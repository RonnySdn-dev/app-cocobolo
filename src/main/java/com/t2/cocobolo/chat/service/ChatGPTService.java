// src/main/java/com/t2/cocobolo/chat/service/ChatGPTService.java
package com.t2.cocobolo.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.t2.cocobolo.chat.dto.OpenAIRequest;
import com.t2.cocobolo.chat.dto.OpenAIResponse;
import com.t2.cocobolo.cliente.service.ClienteService;
import com.t2.cocobolo.pedido.service.PedidoService;
import com.t2.cocobolo.producto.service.ProductoService;

import reactor.core.publisher.Mono;

@Service
public class ChatGPTService {

    private final WebClient webClient;
    private final String model;
    private final ProductoService productoService;
    private final ClienteService clienteService;
    private final PedidoService pedidoService;

    public ChatGPTService(
            @Value("${openai.api.key}") String apiKey,
            @Value("${openai.api.url}") String apiUrl,
            @Value("${openai.model}") String model,
            ProductoService productoService,
            ClienteService clienteService,
            PedidoService pedidoService) {

        System.out.println("🔧 Inicializando ChatGPTService...");
        System.out.println("📍 API URL: " + apiUrl);
        System.out.println("🤖 Modelo: " + model);
        System.out.println("🔑 API Key presente: " + (apiKey != null && !apiKey.isEmpty() ? "Sí" : "No"));

        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();

        this.model = model;
        this.productoService = productoService;
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;

        System.out.println("✅ ChatGPTService inicializado correctamente");
    }

    public String chat(String userMessage) {
        try {
            System.out.println("💬 Procesando mensaje: " + userMessage);

            // Construir contexto dinámico
            String systemContext = buildSystemContext();
            System.out.println("📋 Contexto generado (" + systemContext.length() + " caracteres)");

            List<OpenAIRequest.Message> messages = new ArrayList<>();
            messages.add(new OpenAIRequest.Message("system", systemContext));
            messages.add(new OpenAIRequest.Message("user", userMessage));

            OpenAIRequest request = new OpenAIRequest(model, messages);

            System.out.println("🌐 Enviando petición a OpenAI...");

            OpenAIResponse response = webClient.post()
                    .bodyValue(request)
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> {
                                System.err.println("❌ Error HTTP: " + clientResponse.statusCode());
                                return clientResponse.bodyToMono(String.class)
                                        .flatMap(body -> {
                                            System.err.println("❌ Respuesta de error: " + body);
                                            return Mono.error(new RuntimeException("Error de API: " + body));
                                        });
                            })
                    .bodyToMono(OpenAIResponse.class)
                    .block();

            if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                String botResponse = response.getChoices().get(0).getMessage().getContent();
                System.out.println("✅ Respuesta recibida de OpenAI (" + botResponse.length() + " caracteres)");
                return botResponse;
            }

            System.err.println("⚠️ Respuesta vacía de OpenAI");
            return "Lo siento, no pude procesar tu pregunta en este momento.";

        } catch (Exception e) {
            System.err.println("❌ Error en chat(): " + e.getMessage());
            e.printStackTrace();
            return "Error al comunicarme con el servicio de IA. Por favor, verifica tu API key y conexión.";
        }
    }

    private String buildSystemContext() {
        StringBuilder context = new StringBuilder();

        context.append(
                "Eres un asistente virtual de 'Cocobolo & Cocobaby', una tienda especializada en sets de cuna para bebés.\n\n");

        context.append("INFORMACIÓN DE LA TIENDA:\n");
        context.append("- Nombre: Cocobolo & Cocobaby\n");
        context.append("- Rubro: Venta de sets de cuna personalizados para bebés\n");
        context.append("- Ubicación: Lima, Perú\n");
        context.append("- Moneda: Soles peruanos (S/)\n\n");

        try {
            // Información de productos
            var productos = productoService.listar();
            context.append("PRODUCTOS DISPONIBLES ACTUALMENTE:\n");
            if (!productos.isEmpty()) {
                productos.forEach(p -> {
                    context.append(String.format("- %s: S/ %.2f | Stock: %d unidades | Estado: %s\n",
                            p.getNombre(),
                            p.getPrecioUnitario(),
                            p.getStock(),
                            p.isActivo() ? "Activo" : "Inactivo"));
                });
            } else {
                context.append("- No hay productos registrados actualmente.\n");
            }
            context.append("\n");

            // Estadísticas
            var clientes = clienteService.listar();
            context.append("ESTADÍSTICAS:\n");
            context.append(String.format("- Total de clientes registrados: %d\n", clientes.size()));

            var pedidos = pedidoService.listar();
            context.append(String.format("- Total de pedidos realizados: %d\n", pedidos.size()));

            if (!pedidos.isEmpty()) {
                var totalVentas = pedidos.stream()
                        .map(p -> p.getTotal())
                        .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
                context.append(String.format("- Total vendido: S/ %.2f\n", totalVentas));
            }
            context.append("\n");
        } catch (Exception e) {
            System.err.println("⚠️ Error al construir contexto: " + e.getMessage());
        }

        context.append("FUNCIONALIDADES DEL SISTEMA:\n");
        context.append("1. Gestión de Productos: Crear, editar, activar/desactivar productos\n");
        context.append("2. Gestión de Clientes: Registrar clientes con nombre, email y teléfono\n");
        context.append("3. Gestión de Pedidos: Crear pedidos vinculando cliente y producto\n");
        context.append("4. Descuentos automáticos: 5% si compras 5 o más unidades de un producto\n");
        context.append("5. Control de stock automático\n\n");

        context.append("INSTRUCCIONES:\n");
        context.append("- Responde en español de manera amigable y profesional\n");
        context.append("- Si te preguntan por productos, menciona los disponibles con precios actuales\n");
        context.append("- Si te preguntan sobre pedidos o procesos, explica paso a paso\n");
        context.append("- Si no tienes información específica, sugiere contactar al administrador\n");
        context.append("- Usa emojis ocasionalmente para ser más cercano 👶✨\n");

        return context.toString();
    }
}
