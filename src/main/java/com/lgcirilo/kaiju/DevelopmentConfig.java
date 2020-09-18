// TODO - check task text size
package com.lgcirilo.kaiju;

import com.lgcirilo.kaiju.entities.Task;
import com.lgcirilo.kaiju.entities.User;
import com.lgcirilo.kaiju.repositories.TaskRepository;
import com.lgcirilo.kaiju.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepo,
                                        TaskRepository taskRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                taskRepo.deleteAll();
                userRepo.deleteAll();

                User gustavo = new User("lgcirilo@gmail.com", "Gustavo");
                userRepo.save(gustavo);

                taskRepo.save(new Task(gustavo, "Transferir dinheiro das contas da mamãe. R$ 8636,00"));
                taskRepo.save(new Task(gustavo, "Java Collections"));
                taskRepo.save(new Task(gustavo, "Java Streams"));
                taskRepo.save(new Task(gustavo, "Java Lambdas"));
                taskRepo.save(new Task(gustavo, "Java Design Patterns"));
                taskRepo.save(new Task(gustavo, "Marcar entrevista Immi"));
                taskRepo.save(new Task(gustavo, "Pegar boleto Gab IRPF 2018"));
                taskRepo.save(new Task(gustavo, "Vender ações"));
                taskRepo.save(new Task(gustavo, "Tranferir dinheiro para a caixa. R$ 500,00"));
                taskRepo.save(new Task(gustavo, "Pedir prorrogação no INSS"));
                taskRepo.save(new Task(gustavo, "Buscar dinheiro INSS. Agência Francisco porto. R$ 1045,00"));
                taskRepo.save(new Task(gustavo, "Buscar dinheiro INSS. Agência Santa Catarina. Primerio depósito. R$ ?????"));
                taskRepo.save(new Task(gustavo, "Retificat IRPF mamãe. Colocar dados corretos de 2019 (exercício 2018)"));
                taskRepo.save(new Task(gustavo, "Renovar WES"));
            }
        };
    }

}

