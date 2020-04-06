/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Logic;
import banca.data.CuentaAmigaDAO;
import banca.data.LoginCajeroDAO;
import banca.data.CuentaDAO;
import banca.data.DepositoDAO;

import java.util.List;
import banca.data.LoginclienteDAO;
import banca.data.PersonaDAO;
import banca.data.RetiroDAO;
import banca.data.TransferenciaDAO;
/**
 *
 * @author Escinf
 */
public class Model {
    private static Model uniqueInstance;
    private  banca.data.RelDatabase db;
    private  LoginclienteDAO logincliente;
    private CuentaDAO cuentadao;
    private LoginCajeroDAO logincajero;
    private CuentaAmigaDAO cuentaamiga;
    private PersonaDAO personadao;
    private TransferenciaDAO transferenciadao;
    private DepositoDAO depositodao;
    private RetiroDAO retirodao;
    public static Model instance(){
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance; 
    }

    
    public Model(){
db = new banca.data.RelDatabase(); 
logincliente = new LoginclienteDAO();
cuentadao = new CuentaDAO();
logincajero= new LoginCajeroDAO();
cuentaamiga = new CuentaAmigaDAO();
personadao = new PersonaDAO();
transferenciadao = new TransferenciaDAO();
depositodao=new DepositoDAO();
retirodao=new RetiroDAO();
    }
public Login_usuario ClienteFind(String cedula, String contraseña) throws Exception
{
return logincliente.LoginclienteGet(cedula, contraseña, db);
}
public boolean Ispersona(String cedula) throws Exception
{
return personadao.Ispersona(cedula, db);
}
public List<Cuenta> cuentasFind(Login_usuario cliente)
{
return cuentadao.CuentaSearch(cliente.cedula, db);
}
public boolean Iscliente(String cedula) throws Exception
{
return logincliente.Iscliente(cedula, db);
}
public Login_usuario CajeroFind(String cedula, String contraseña) throws Exception
{
return logincajero.LogincajeroGet(cedula, contraseña, db);
}
public boolean Iscajero(String cedula) throws Exception
{
return logincajero.Iscajero(cedula, db);
}
public void Addpersona(String Cedula, String Nombre, String Telefono)throws Exception
{
 logincliente.PersonaAdd(Cedula, Nombre, Telefono, db);
}

public boolean Iscuenta(String numero)
{
    return cuentadao.IsCuenta(numero, db);
}

public void Addcuenta(Cuenta cuenta) throws Exception
{
cuentadao.CuentaAdd(cuenta, db);
}
public void AddLoginCliente(String cedula,String Contraseña) throws Exception
{
logincliente.Loginclienteadd(cedula, Contraseña, db);
}
public void AddCuentaAmiga(CuentaAmiga cuenta_amiga) throws Exception
{
cuentaamiga.CuentaAdd(cuenta_amiga, db);
}
public Cuenta CuentaGet(String Nmuero)
{
return cuentadao.CuentaGet(Nmuero, db);
}
public List<CuentaAmiga> CuentasAmigaSearch(String cedula) throws Exception
{
return cuentaamiga.CuentaSearch(cedula, db);
}
public boolean IsCuentaAmiga(String origen,String amiga)
{
return cuentaamiga.IsCuentaAmiga(origen, amiga, db);
}
public Persona PersonaGet(String cedula) throws Exception
{
return personadao.PersonaGet(cedula, db);
}
public void CuentaUpdate(double saldo,String Numero) throws Exception
{
cuentadao.CuentaUpdate(db,Numero,saldo);
}
public List<Transferencia> TransferenciaSearchRemota(String Numero)
{
return transferenciadao.TransferenciaSearchremota(Numero, db);
}
public void TransferenciaAdd(Transferencia transfrencia) throws Exception
{
transferenciadao.TransaccionAdd(transfrencia, db);
}
public void DepositoAdd(Deposito deposito) throws Exception
{
depositodao.CuentaAdd(deposito, db);
}
public void RetiroAdd(Retiro retiro) throws Exception
{
retirodao.RetiroAdd(retiro, db);
}
public List<Transferencia> TransferenciaSearchFecha(String Numero,String FechaA,String FechaB)
{
return transferenciadao.Transferenciasearchfecha(Numero, FechaA, FechaB, db);
} 
public List<Transferencia> TransferenciaSearch(String Numero)
{
return transferenciadao.Transferenciasearch(Numero, db);
}
public List<Deposito> DepositoSearchFecha(String Numero,String FechaA,String FechaB)
{
return depositodao.Depositosearchfecha(Numero, FechaA, FechaB, db);
}
public List<Deposito> DepositoSearch(String Numero)
{
return depositodao.Depositosearch(Numero, db);
}
public List<Retiro> RetiroSearchFecha(String Numero,String FechaA,String FechaB)
{
return retirodao.Retirosearchfecha(Numero, FechaA, FechaB, db);
}
public List<Retiro> RetiroSearch(String Numero)
{
return retirodao.Retirosearch(Numero, db);
}
public List<Cuenta> Cuentasget()
{
return cuentadao.CuentasGet(db);
}

}
