package com.sistemavidaplus.aspect;
import com.sistemavidaplus.entity.LogEntity;
import com.sistemavidaplus.entity.UsuarioEntity;
import com.sistemavidaplus.repository.UsuarioRepository;
import com.sistemavidaplus.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;

// A classe LogAspect captura todas as chamadas dos controllers para registrar nos logs.
// exceto as chamadas feitas pelo próprio LogController

@Aspect
@Component
public class LogAspect {

    private final LogService logService;
    private final UsuarioRepository usuarioRepository;

    public LogAspect(LogService logService, UsuarioRepository usuarioRepository) {
        this.logService = logService;
        this.usuarioRepository = usuarioRepository;
    }

    @Around("execution(* com.sistemavidaplus.controller.*.*(..)) && " +
            "!within(com.sistemavidaplus.controller.LogController)")
    public Object registrarAcao(ProceedingJoinPoint joinPoint) throws Throwable {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = (principal instanceof User) ? ((User) principal).getUsername() : principal.toString();


        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByLogin(username);
        UsuarioEntity usuarioEntityLogado = usuarioOpt.orElse(null);

        Object result = joinPoint.proceed();

        String acaoRealizada = joinPoint.getSignature().toShortString();
        String descricao = "Ação realizada: " + acaoRealizada;
        LogEntity logEntityEntry = new LogEntity(LocalDateTime.now(), descricao, "INFO", usuarioEntityLogado);
        logService.salvarOuAtualizar(logEntityEntry);

        return result;
    }
}