package test;

import dao.ProprietarioDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.ProprietarioDTO;
import util.ConexaoApi;

/**
 *
 * @author matheus
 */
public class Tester {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConexaoApi conn = new ConexaoApi();
        Scanner in = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("Digite 1 para inserir um novo proprietario ");
            System.out.println("Digite 2 para consultar um proprietário ");
            System.out.println("Digite 3 para consultar todos os proprietários ");
            System.out.println("Digite 4 para alterar um proprietario ");
            System.out.println("Digite 5 para deletar um proprietario ");
            System.out.println("Digite 0 para sair.");
            opcao = in.nextInt();

            switch (opcao) {
                case 1: {
                    inserirNovoProprietario(conn, in);
                    break;
                }

                case 2: {
                    consultarProprietario(conn, in);
                    break;
                }

                case 3: {
                    consultarTodosOsProprietarios(conn);
                    break;
                }

                case 4: {
                    alterarProprietario(conn, in);
                    break;
                }

                case 5: {
                    removerProprietario(conn, in);
                    break;
                }

                default:
                    break;
            }
        } while (opcao != 0);
    }

    private static void inserirNovoProprietario(ConexaoApi conn, Scanner in) throws SQLException, ClassNotFoundException {
        ProprietarioDAO pDAO = new ProprietarioDAO();
        ProprietarioDTO prop = new ProprietarioDTO();
        System.out.println("Informe o nome: ");
        String input = in.next();
        prop.setNome(input);

        System.out.println("Informe o sobrenome: ");
        input = in.nextLine();
        prop.setSobrenome(input);

        System.out.println("Informe o sexo: ");
        input = in.nextLine();
        prop.setSexo(input);

        System.out.println("Informe o cpf: ");
        input = in.nextLine();
        prop.setCpf(input);

        System.out.println("Informe o telefone: ");
        input = in.nextLine();
        prop.setTelefone(input);

        System.out.println("Informe o Email: ");
        input = in.nextLine();
        prop.setEmail(input);

        System.out.println("Informe o id endereço: ");
        int inputInt = in.nextInt();
        prop.setIdEndereco(inputInt);
        
        pDAO.save(conn, prop);
        conn.commit();
        System.out.println("Inserido com sucesso.");
    }

    private static void consultarProprietario(ConexaoApi conn, Scanner in) throws SQLException, ClassNotFoundException {
        ProprietarioDAO pDAO = new ProprietarioDAO();
        System.out.println("Informe o ID do proprietário: ");
        int id = in.nextInt();
        ProprietarioDTO proprietarioDTO = pDAO.getOne(conn, id);
        System.out.println(proprietarioDTO);
    }

    private static void consultarTodosOsProprietarios(ConexaoApi conn) throws SQLException, ClassNotFoundException {
        ProprietarioDAO pDAO = new ProprietarioDAO();
        List<ProprietarioDTO> proprietarioDTO = pDAO.getAll(conn);
        proprietarioDTO.forEach(System.out::println);
    }
    
    private static void alterarProprietario(ConexaoApi conn, Scanner in) throws SQLException, ClassNotFoundException {
        ProprietarioDAO pDAO = new ProprietarioDAO();
        System.out.println("Informe o ID do proprietário que vocẽ gostaria de alterar: ");
        int id = in.nextInt();
        ProprietarioDTO prop = pDAO.getOne(conn, id);
        if(prop == null)  {
            System.out.println("Proprietario não existe. ");
            return;
        }
        
        int opcao;
        do {
            System.out.println("Digite o número correspondete a coluna que deseja alterar: (0 - para ALTERAR) ");
            System.out.println("1 - nome ");
            System.out.println("2 - sobrenome");
            System.out.println("3 - sexo");
            System.out.println("4 - cpf");
            System.out.println("5 - telefone");
            System.out.println("6 - email");
            System.out.println("7 - id endereço ");
            opcao = in.nextInt();
            switch (opcao) {
                case 1 : {
                    System.out.println("Informe o nome: ");
                    String input = in.nextLine();
                    prop.setNome(input);
                    break;
                }
                
                case 2 : {
                    System.out.println("Informe o sobrenome: ");
                    String input = in.nextLine();
                    prop.setSobrenome(input);
                    break;
                }
                
                case 3 : {
                    System.out.println("Informe o sexo: (somente: M ou F) ");
                    String input = in.nextLine();
                    prop.setSexo(input);
                    break;
                }
                
                case 4 : {
                    System.out.println("Informe o cpf: ");
                    String input = in.nextLine();
                    prop.setCpf(input);
                    break;
                }
                
                case 5 : {
                    System.out.println("Informe o telefone: ");
                    String input = in.nextLine();
                    prop.setTelefone(input);
                    break;
                }
                
                case 6 : {
                    System.out.println("Informe o Email: ");
                    String input = in.nextLine();
                    prop.setEmail(input);
                    break;
                }
                
                case 7 : {
                    System.out.println("Informe o id endereço: ");
                    int input = in.nextInt();
                    prop.setIdEndereco(input);
                }
                default : break;
            }
        } while(opcao != 0);
        pDAO.save(conn, prop);
        conn.commit();
        
        System.out.println("Alterado com sucesso.");
    }
    
     private static void removerProprietario(ConexaoApi conn, Scanner in) throws SQLException, ClassNotFoundException {
         System.out.println("Informe o ID: ");
         int id = in.nextInt();
         
         ProprietarioDAO dao = new ProprietarioDAO();
         ProprietarioDTO dto = dao.getOne(conn, id);
         dao.delete(conn, dto);
         conn.commit();
         System.out.println("Deletado com sucesso.");
     }
}
