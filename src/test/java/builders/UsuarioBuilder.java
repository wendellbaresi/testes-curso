package builders;

import br.ce.wcaquino.dto.UsuarioDTO;

public class UsuarioBuilder {

    private UsuarioDTO usuarioDTO;

    private UsuarioBuilder(){

    }

    public static UsuarioBuilder umUsuario(){
        UsuarioBuilder builder = new UsuarioBuilder();

        builder.usuarioDTO = new UsuarioDTO();
        builder.usuarioDTO.setNome("Usuario 1");
        return builder;
    }

    public UsuarioDTO agora(){
        return usuarioDTO;
    }
}
