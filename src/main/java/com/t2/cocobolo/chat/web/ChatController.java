// src/main/java/com/t2/cocobolo/chat/web/ChatController.java
package com.t2.cocobolo.chat.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.t2.cocobolo.chat.dto.ChatRequest;
import com.t2.cocobolo.chat.dto.ChatResponse;
import com.t2.cocobolo.chat.service.ChatGPTService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Permitir CORS si fuera necesario
public class ChatController {

    private final ChatGPTService chatGPTService;

    public ChatController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        try {
            System.out.println("📩 Mensaje recibido: " + request.getMessage()); // LOG para debug

            if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(ChatResponse.error("El mensaje no puede estar vacío"));
            }

            String response = chatGPTService.chat(request.getMessage());

            System.out.println("✅ Respuesta generada: " + response); // LOG para debug

            return ResponseEntity.ok(new ChatResponse(response));

        } catch (Exception e) {
            System.err.println("❌ Error en ChatController: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(ChatResponse.error("Error al procesar la solicitud: " + e.getMessage()));
        }
    }

    // Endpoint de prueba para verificar que funciona
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("✅ ChatController está funcionando!");
    }
}