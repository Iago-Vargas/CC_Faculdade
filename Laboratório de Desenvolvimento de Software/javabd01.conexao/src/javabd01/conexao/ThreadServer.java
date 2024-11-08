/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabd01.conexao;

import DAO.PessoaDAO;
import beans.Pessoa;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iagov
 */
public class ThreadServer extends Thread{
    private Socket clienteSocket;
    
    public ThreadServer(Socket clienteSocket){
        this.clienteSocket = clienteSocket;
    }
    @Override
    public void run(){
        try(ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream (clienteSocket.getInputStream())){
            //Consultar
            //int id = in.readInt();
            //System.out.println ("ID recebido: "+id);
            //PessoaDAO pdao = new PessoaDAO();
            //Pessoa p = pdao.getPessoa(id);
            //out.writeObject(p);
            //Consultar fim
            
            //Cadastrar:
            Pessoa p = (Pessoa) in.readObject();
            PessoaDAO pDAO = new PessoaDAO();
            pDAO.inserir(p);
            
        }catch (IOException ex){
            System.out.println ("Erro ao lidar com o cliente");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
