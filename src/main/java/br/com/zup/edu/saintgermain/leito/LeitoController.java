package br.com.zup.edu.saintgermain.leito;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(LeitoController.BASE_URI)
public class LeitoController {

    public final static String BASE_URI = "/leitos";

    private final LeitoRepository leitoRepository;

    public LeitoController(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }

    @Transactional
    @PatchMapping("/{leitoId}")
    public ResponseEntity<Void> reservar(@PathVariable Long leitoId) {
        Leito leito = leitoRepository.findById(leitoId)
                                     .orElseThrow(
                                         () -> new ResponseStatusException(
                                             HttpStatus.NOT_FOUND,
                                             "Não existe um leito com o id fornecido."
                                         )
                                     );

        leito.reservar();

        return ResponseEntity.noContent().build();
    }

}
