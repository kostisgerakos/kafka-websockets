package com.kostisgerakos.kafkatoolbackend.controller;

import com.kostisgerakos.kafkatoolbackend.service.ProducerService;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Kafka/Topics")
public final class KafkaController {
    private final ProducerService producerService;

    public KafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }

	@PostMapping(value = "/FusionAlert")
	public void sendFusionAlert(@RequestParam Integer alert_id)// ,@RequestParam Location location)
	{
	}

	
	

}
