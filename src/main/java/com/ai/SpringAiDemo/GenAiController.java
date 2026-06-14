package com.ai.SpringAiDemo;

import lombok.AllArgsConstructor;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GenAiController {

    private final ChatService chatService;
    private final ImageService imageService;

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("generate-image")
    public String generateImage(@RequestParam String prompt){
        ImageResponse imageResponse = imageService.generateImage(prompt);

        String base64Data = imageResponse.getResult().getOutput().getB64Json();

        System.out.println(base64Data);
        if (base64Data == null || base64Data.isBlank()) {
            throw new IllegalStateException("No image data payload retrieved from the model engine.");
        }

        // Return a raw HTML rendering view to cleanly render the image on the client browser
        return """
               <html>
                <body style="background:#121212; display:flex; justify-content:center; align-items:center; height:100vh; margin:0; font-family:sans-serif; color:white;">
                    <div style="text-align:center;">
                        <h2>Generated Output</h2>
                        <img src="data:image/png;base64,%s" style="border-radius:8px; box-shadow: 0 4px 15px rgba(0,0,0,0.5); max-width:90vw; max-height:80vh;" />
                        <p style="color:#888; margin-top:15px;">Prompt: "%s"</p>
                    </div>
                </body>
               </html>
               """.formatted(base64Data, prompt);
    }

    @GetMapping("generate-image-options")
    public List<String> generateImageOptions(@RequestParam String prompt) {
        ImageResponse imageResponse = imageService.generateImageOptions(prompt);

        List<String> images = imageResponse.getResults().stream()
                .map(image -> image.getOutput().getB64Json())
                .toList();

        return images;
    }

}
