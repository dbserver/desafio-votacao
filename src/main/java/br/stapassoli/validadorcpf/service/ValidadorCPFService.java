package br.stapassoli.validadorcpf.service;

import br.stapassoli.validadorcpf.dto.NumeroCpfDTOResposta;
import br.stapassoli.validadorcpf.enums.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;

@Service
public class ValidadorCPFService {

    private List<String> getCpfInvalidos(){
        return List.of(
                "00000000000",
                "11111111111",
                "22222222222",
                "33333333333",
                "44444444444",
                "55555555555",
                "66666666666",
                "77777777777",
                "88888888888",
                "99999999999"
            );
    }

    public ResponseEntity<NumeroCpfDTOResposta> validarCPF(String CPF){

        if(isCPF(CPF)){
            return ResponseEntity.ok().body(NumeroCpfDTOResposta.builder().status(Status.ABLE_TO_VOTE).build());
        }

        return ResponseEntity.badRequest().body(NumeroCpfDTOResposta.builder().status(Status.UNABLE_TO_VOTE).build());
    }

    private boolean isCPF(String CPF) {

        boolean algumCpfInvalido = getCpfInvalidos().stream().anyMatch(cpfInvalido -> cpfInvalido.equals(CPF));
        boolean cpfComNumeroIncorretoDeCaracteres = CPF.length() != 11;

        if(algumCpfInvalido || cpfComNumeroIncorretoDeCaracteres){
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }

    }

    public String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

}
