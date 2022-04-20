package br.com.zup.edu.saintgermain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.zup.edu.saintgermain.leito.LeitoRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final LeitoRepository leitoRepository;

    public DataLoader(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (leitoRepository.count() == 0) {
            load();
        }
    }

    private void load() {

    }

}
