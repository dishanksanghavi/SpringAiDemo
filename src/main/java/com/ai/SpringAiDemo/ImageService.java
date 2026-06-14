package com.ai.SpringAiDemo;

import lombok.AllArgsConstructor;
import org.springframework.ai.image.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageModel imageModel;

    public ImageResponse generateImage(String prompt) {
        ImageOptions imageOptions = ImageOptionsBuilder.builder()
                .model("gpt-image-2")
                .build();

        return imageModel.call(new ImagePrompt(prompt, imageOptions));
    }

    public ImageResponse generateImageOptions(String prompt) {
        ImageOptions imageOptions = ImageOptionsBuilder.builder()
                .model("gpt-image-2")
                .n(3)
                .build();

        return imageModel.call(new ImagePrompt(prompt, imageOptions));
    }
}
