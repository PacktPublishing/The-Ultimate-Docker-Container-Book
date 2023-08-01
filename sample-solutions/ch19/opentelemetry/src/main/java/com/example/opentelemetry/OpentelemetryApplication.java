package com.example.opentelemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;

@SpringBootApplication
@RestController
public class OpentelemetryApplication {
	private static final String INSTRUMENTATION_NAME = OpentelemetryApplication.class.getName();
	private static Tracer tracer;

	public static void main(String[] args) {
		OpenTelemetry openTelemetry = AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
		tracer = openTelemetry.getTracer(INSTRUMENTATION_NAME);

		SpringApplication.run(OpentelemetryApplication.class, args);
	}


	// Do some real work that'll emit telemetry
	@GetMapping("/test")
	public String test() throws InterruptedException {
		Span span = tracer
				.spanBuilder("important work")
				.setAttribute("foo", 42)
				.setAttribute("bar", "a string!")
				.startSpan();
		try {
			Thread.sleep(1000);
			return "Called 'test'";
		} finally {
			span.end();
		}
	}
}
