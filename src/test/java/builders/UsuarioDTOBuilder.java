package builders;

import com.projetotestes.dto.UsuarioDTO;

public class UsuarioDTOBuilder {

    private UsuarioDTO usuarioDTO;

    private UsuarioDTOBuilder(){}

    public static UsuarioDTOBuilder umUsuarioDTO(){
        UsuarioDTOBuilder builder = new UsuarioDTOBuilder();

        builder.usuarioDTO = new UsuarioDTO();
        builder.usuarioDTO.setNome("Usuario 1");
        return builder;
    }

    public UsuarioDTO agora(){
        return usuarioDTO;
    }
}
