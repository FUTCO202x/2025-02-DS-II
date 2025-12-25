package spring_security.plataforma_online.controller.dto;

import java.util.List;

public class CreateCartRequest {
    private List<Long> productoIds;

    public CreateCartRequest() {
    }

    public List<Long> getProductoIds() {
        return productoIds;
    }

    public void setProductoIds(List<Long> productoIds) {
        this.productoIds = productoIds;
    }
}
