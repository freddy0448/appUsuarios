
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class querys extends frm_singup
{

    private JTable jtb_tabla_registros_lin;
    DefaultTableModel modelo = new DefaultTableModel();
    
    private void tablas()
        {
            modelo.addColumn("USUARIO");
            modelo.addColumn("NOMBRE");
            modelo.addColumn("APELLIDO");
            modelo.addColumn("NUMERO");
            modelo.addColumn("NUMERO");
            modelo.addColumn("EMAIL");
            jtb_tabla_registros_lin = new JTable(modelo);

        }

    public boolean registrar(frm_singup datos)
    {
        PreparedStatement ps = null;
        Connection con = establecerCon();
        
        String sql = "INSERT INTO usuarios (nm_usuario, nombre, apellido, numero, email, password)"
        +"VALUES (?,?,?,?,?,?)";
        
        try 
        {
            ps = con.prepareStatement(sql);
            ps.setString(1, datos.getNm_usuario());
            ps.setString(2, datos.getNombre());
            ps.setString(3, datos.getApellido());
            ps.setString(4, datos.getNumero());
            ps.setString(5, datos.getEmail());
            ps.setString(6, datos.getContra());
            ps.execute();

            return true;

        }
         catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                con.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        
    }


    public void mostrar_tabla(frm_singup objeto)
    {
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        String [] datos = new String[5];

        try 
        {
            cn = objeto.establecerCon();
            stm = cn.createStatement();
            rs = stm.executeQuery("SELECT * FROM usuarios");

            while(rs.next())
            {
                datos[0]=rs.getString(2);
                datos[1]=rs.getString(3);
                datos[2]=rs.getString(4);
                datos[3]=rs.getString(5);
                datos[4]=rs.getString(6);
                modelo.addRow(datos);
                
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }



}

















/* public boolean mostrar(frm_singup datos)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = establecerCon();
        
        String sql = "SELECT* FROM usuarios;";
        
        try 
        {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next())
            {

                String [] reslt = new String[5];
                reslt[0] =rs.getString(2);
                reslt[1] =rs.getString(3);
                reslt[2] =rs.getString(4);
                reslt[3] =rs.getString(5);
                reslt[4] =rs.getString(6);
                

            }
            return true;

        }
         catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                con.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        
    } */