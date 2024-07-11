

package com.aluracursos.forohub.domain.user;

public record UsuarioDTO(
        Long id,
        String nombre,
        String email
) {
    public UsuarioDTO(User usuario) {
        this(usuario.getId(), usuario.getName(), usuario.getEmail());
    }

}

