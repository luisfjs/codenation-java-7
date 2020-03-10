package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estacionamento {
    private static int VAGAS = 10;
    private List<Carro> carrosEstacionados = new ArrayList<>();


    public void estacionar(Carro carro) {
        if(Objects.isNull(carro.getMotorista()))
            throw new EstacionamentoException("Para entrar no estacionamento, é necessário que exista um motorista, ou seja, nada de carro autônomo.");

        if(carro.getMotorista().getIdade() < 18)
            throw new EstacionamentoException("O motorista precisa ter idade suficiente para dirigir e possuir uma habilitação.");

        if(carro.getMotorista().getPontos() > 20)
            throw new EstacionamentoException("A habilitação não deverá está suspensa, ou seja, a pontuação da carteira de motorista não deverá ser superior a vinte pontos.");

        if(carrosEstacionados() == VAGAS) {
            Carro carroParaRemover = carrosEstacionados.stream()
                    .filter(carroEstacionado -> carroEstacionado.getMotorista().getIdade() < 55)
                    .findFirst()
                    .orElseThrow(() -> new EstacionamentoException("Caso todos os motoristas, dentro do estacionamento, tenham mais de 55 anos e chegue um motorista, ele não conseguirá estacionar."));

            carrosEstacionados.remove(carroParaRemover);
            carrosEstacionados.add(carro);
        } else {
            carrosEstacionados.add(carro);
        }
    }

    public int carrosEstacionados() {
        return carrosEstacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carrosEstacionados.contains(carro);
    }
}
