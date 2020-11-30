package principal;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import entity.Colaborador;
import view.JanelaListagem;

public class Principal {

    public static void main(String args[]) {

        Colaborador col1 = new Colaborador("ARTHUR FELIPE R. COSTA", LocalDate.of(1984, Month.APRIL, 15));
        //15/04/1984

        Colaborador col2 = new Colaborador("DIOGO FERNANDES DE SOUZA", LocalDate.of(1989, Month.JANUARY, 22));
        //22/01/1989

        Colaborador col3 = new Colaborador("ELIVELTON COSTA DA SILVA", LocalDate.of(1986, Month.DECEMBER, 2));
        //02/12/1986

        Colaborador col4 = new Colaborador("ANA TEREZA DE A. VASQUES", LocalDate.of(1997, Month.JANUARY, 27));
        //27/01/1997

        Colaborador col5 = new Colaborador("MARIA CONCEIÇÃO DA S. SOUZA", LocalDate.of(1980, Month.APRIL, 19));
        //19/04/1980

        Colaborador col6 = new Colaborador("JOAQUIM PEREIRA", LocalDate.of(1983, Month.APRIL, 5));
        //05/04/1983

        Colaborador col7 = new Colaborador("ANA FERREIRA DA CUNHA", LocalDate.of(1983, Month.APRIL, 5));
        //05/04/1983

        Colaborador col8 = new Colaborador("GILBERTO OLIVEIRA", LocalDate.of(1992, Month.MARCH, 18));
        //18/03/1992

        Colaborador col9 = new Colaborador("GILBERTO OLIVEIRA", LocalDate.of(1972, Month.JUNE, 10));
        //10/06/1972

        Colaborador col10 = new Colaborador("GUILHERME AZEVEDO REIS", LocalDate.of(1998, Month.JUNE, 23));
        //23/06/1998

        Colaborador col11 = new Colaborador("GLAUCIA ADRIANA DANTAS PEREIRA", LocalDate.of(1994, Month.JUNE, 13));
        //13/06/1994

        List<Colaborador> colaboradores = new ArrayList<>();
        colaboradores.add(col1);
        colaboradores.add(col2);
        colaboradores.add(col3);
        colaboradores.add(col4);
        colaboradores.add(col5);
        colaboradores.add(col6);
        colaboradores.add(col7);
        colaboradores.add(col8);
        colaboradores.add(col9);
        colaboradores.add(col10);
        colaboradores.add(col11);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JanelaListagem(colaboradores);
            }
        });
    }
}
