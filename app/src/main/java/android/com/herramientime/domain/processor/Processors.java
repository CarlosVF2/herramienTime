package android.com.herramientime.domain.processor;

public class Processors {

    public ProcessorHerramienta getProcessorHerramienta() {
        return new ProcessorHerramienta();
    }

    public ProcessorExperiencia getProcessorExperiencia() {
        return new ProcessorExperiencia();
    }

    public ProcessorUsuario getProcessorUsuario() {
        return new ProcessorUsuario();
    }

    public ProcessorCategoria getProcessorCategoria() {
        return new ProcessorCategoria();
    }

    public ProcessorMoneda geProcessorMoneda() {
        return new ProcessorMoneda();
    }
}
