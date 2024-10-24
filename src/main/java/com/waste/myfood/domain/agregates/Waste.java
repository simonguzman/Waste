package com.waste.myfood.domain.agregates;

import java.util.ArrayList;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Waste {
    private String idWaste;
    private String productId;
    private QuantityWaste quantityWaste;
    private CauseWaste cause;
    private Date dateRegister; 
    private List<Product> productsWaste;

    public Waste(Product product, double initialQuantityWaste, CauseWaste cause){
        this.idWaste = UUID.randomUUID().toString();
        this.productId = product.getId();
        this.quantityWaste = new QuantityWaste(initialQuantityWaste);
        this.cause = cause;
        this.dateRegister = new Date();
        this.productsWaste = new ArrayList<>();
    }

    public Waste() {
        this.idWaste = UUID.randomUUID().toString();  
        this.productId = "";  
        this.quantityWaste = new QuantityWaste(0);  
        this.cause = new CauseWaste();  
        this.dateRegister = new Date();  
        this.productsWaste = new ArrayList<>();  
    }

   /**
     * Registra una cantidad de desperdicio para el objeto Waste.
     *
     * @param quantity La cantidad de desperdicio a registrar.
     * @throws IllegalArgumentException Si la cantidad es negativa o cero.
     */
    public void registerWaste(double quantity) {
        if (quantity > 0)
            this.quantityWaste.addQuantity(quantity);
        else
            throw new IllegalArgumentException("La cantidad de desperdicio debe ser positiva.");
    }

    /**
     * Sugiere medidas para la reducción del desperdicio basado en la cantidad total de desperdicio registrado
     * y la causa del desperdicio.
     *
     * @return Una cadena que contiene las sugerencias para la reducción del desperdicio.
     */
    public String suggestReductionMeasures() {
        StringBuilder suggestions = new StringBuilder();
        double totalWaste = this.quantityWaste.getTotalWasteQuantity();
        if (totalWaste > 75)
            suggestions.append("Reducción sugerida: La cantidad de desperdicio es extremadamente alta. Reevalúa la planificación.\n");
        else if (totalWaste > 50)
            suggestions.append("Reducción sugerida: Revisa los procedimientos para evitar sobrepreparación o mal almacenamiento.\n");
        else if (totalWaste > 25)
            suggestions.append("Reducción sugerida: Podría ser necesario ajustar la gestión del inventario y las porciones.\n");
        else
            suggestions.append("Reducción sugerida: El nivel de desperdicio es bajo, sigue con las buenas prácticas.\n");

        String causeDescription = this.cause.getDescription();
        String suggestion = CauseWasteConstants.CAUSE_SUGGESTIONS.getOrDefault(causeDescription, "Sugerencia: Analiza más a fondo las causas para proponer acciones correctivas.");
        suggestions.append(suggestion).append("\n");

        return suggestions.toString();
    }
}
