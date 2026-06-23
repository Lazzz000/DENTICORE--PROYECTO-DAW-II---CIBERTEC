package com.cibertec.denticore.security.config;

import com.cibertec.denticore.security.entities.Rol;
import com.cibertec.denticore.security.entities.Usuario;
import com.cibertec.denticore.security.entities.UsuarioRol;
import com.cibertec.denticore.security.repositories.RolRepository;
import com.cibertec.denticore.security.repositories.UsuarioRepository;
import com.cibertec.denticore.security.repositories.UsuarioRolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DataSeedConfig {

    @Bean
    @Transactional
    public CommandLineRunner seedDatabase(
            RolRepository rolRepository,
            UsuarioRepository usuarioRepository,
            UsuarioRolRepository usuarioRolRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            // 1. Inyectar Roles Base
            Rol rolAdmin = rolRepository.findByNombre("ADMINISTRADOR")
                    .orElseGet(() -> rolRepository.save(Rol.builder().nombre("ADMINISTRADOR").activo(true).build()));
            
            Rol rolOdontologo = rolRepository.findByNombre("ODONTOLOGO")
                    .orElseGet(() -> rolRepository.save(Rol.builder().nombre("ODONTOLOGO").activo(true).build()));
            
            Rol rolPaciente = rolRepository.findByNombre("PACIENTE")
                    .orElseGet(() -> rolRepository.save(Rol.builder().nombre("PACIENTE").activo(true).build()));

            // 2. Inyectar Usuario Administrador
            String dniAdmin = "71234567";
            if (usuarioRepository.findByDni(dniAdmin).isEmpty()) {
                Usuario admin = Usuario.builder()
                        .nombreUsuario("admin_master")
                        .dni(dniAdmin)
                        .tipoDocumentoSunat("1") // OBLIGATORIO PARA EVITAR RESTRICCIÓN NOT NULL
                        .nombres("Carlos Miguel")
                        .apellidos("Administrador")
                        .correo("carlos@denticore.com")
                        .passwordHash(passwordEncoder.encode("Admin123!"))
                        .activo(true)
                        .build();

                usuarioRepository.save(admin);

                // 3. Vincular Rol
                UsuarioRol.UsuarioRolId idRelacion = new UsuarioRol.UsuarioRolId(admin.getId(), rolAdmin.getId());
                UsuarioRol relacion = UsuarioRol.builder()
                        .id(idRelacion)
                        .usuario(admin)
                        .rol(rolAdmin)
                        .activo(true)
                        .build();

                usuarioRolRepository.save(relacion);
                
                System.out.println("✅ DENTI-15: Data Seeder ejecutado. Administrador (DNI: 71234567) listo.");
            }
        };
    }
}