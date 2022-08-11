

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;



public class frm_singup extends JFrame {
    private JLabel jlb_usuario_sup, jlb_nombre_sup, jlb_apellido_sup, jlb_numero_sup, jlb_email_sup, jlb_password_sup, jlb_passwordconf_sup, jlb_registro_sup, jlb_msg_sup;
    private JTextField jtf_usuario_sup, jtf_nombre_sup, jtf_apellido_sup, jtf_numero_sup, jtf_email_sup;
    private JPasswordField jpw_password_sup, jpw_passwordconf_sup;
    private JPanel jpn_singup;
    private JButton jbt_registrarse;

    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/tarea4";
    private static final String USER = "root" ;
    private static final String PASSWORD ="freddy04122002";

    public String n_usuario;
    public String nombre;
    public String apellido;
    public String numero;
    public String email;
    public String contra;
    public String contra_conf;

   
   
    private void labels()
    {
        jlb_usuario_sup = new JLabel("Nombre de usuario:");
        jlb_usuario_sup.setBounds(40, 80, 200, 20);

        jlb_nombre_sup = new JLabel("Nombre:");
        jlb_nombre_sup.setBounds(40, 100, 80, 20);

        jlb_apellido_sup = new JLabel("Apellido:");
        jlb_apellido_sup.setBounds(40, 120, 80, 20);

        jlb_numero_sup = new JLabel("Número de teléfono:");
        jlb_numero_sup.setBounds(40, 140, 130, 20);

        jlb_email_sup = new JLabel("Correo electrónico:");
        jlb_email_sup.setBounds(40, 160, 130, 20);

        jlb_password_sup = new JLabel("Contraseña:");
        jlb_password_sup.setBounds(40, 180, 80, 20);

        jlb_passwordconf_sup = new JLabel("Confirme la contraseña:");
        jlb_passwordconf_sup.setBounds(40, 200, 150, 20);
        
        jlb_registro_sup = new JLabel("Ingresa los siguientes datos para registrarte");
        jlb_registro_sup.setBounds(20, 30, 250, 20);

        jlb_msg_sup = new JLabel();
        jlb_msg_sup.setBounds(50, 250, 400, 20);

    }

    private void textfield()
    {
        jtf_usuario_sup = new JTextField();
        jtf_usuario_sup.setBounds(190, 80, 200, 20);
        
        jtf_nombre_sup = new JTextField();
        jtf_nombre_sup.setBounds(190, 100, 200, 20);
        
        jtf_apellido_sup = new JTextField();
        jtf_apellido_sup.setBounds(190, 120, 200, 20);
        
        jtf_numero_sup = new JTextField();
        jtf_numero_sup.setBounds(190, 140, 200, 20);
        
        jtf_email_sup = new JTextField();
        jtf_email_sup.setBounds(190, 160, 200, 20);
    }

    private void password()
    {
        jpw_password_sup = new JPasswordField();
        jpw_password_sup.setBounds(190, 180, 200, 20);

        jpw_passwordconf_sup = new JPasswordField();
        jpw_passwordconf_sup.setBounds(190, 200, 200, 20);
    }

    private void panel()
    {
        jpn_singup = new JPanel();
        jpn_singup.setLayout(null);
    }

    private void buttom()
    {
        jbt_registrarse = new JButton("Registrarse!");
        jbt_registrarse.setBounds(500, 250,120, 20);
    }

    private void inicializar()
    {
        labels();
        textfield();
        password();
        panel();
        buttom();
    }


/* 
    private void consulta()
    {
        frm_singup conexion = new frm_singup();
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        
        try 
        {
            cn = conexion.establecerCon();
            stm = cn.createStatement();
            rs=stm.executeQuery("SELECT * FROM usuarios");

            while (rs.next())
            {
                String nm_usuario = rs.getString(2);
                String nombre = rs.getString(3);
                String apellido = rs.getString(4);
                int number = rs.getInt(5);
                String email = rs.getString(6);
                JOptionPane.showMessageDialog(null,nm_usuario+" "+nombre+" "+apellido+" "+number+" "+email);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null)
                {   
                    rs.close();
                }
                if(stm != null)
                {
                    stm.close();
                }
                if (cn != null)
                {
                    cn.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
 */    
public boolean registrar_datos()
{
    frm_singup datos = new frm_singup();
    PreparedStatement ps = null;
    Connection con = establecerCon();
    
    String sql = "INSERT INTO usuarios (nm_usuario, nombre, apellido, numero, email, password)"
    +"VALUES ("+jtf_usuario_sup+", "+jtf_nombre_sup+", "+jtf_apellido_sup+", "+jtf_numero_sup+", "+jtf_email_sup+", "+jpw_password_sup.getPassword()+");";
    
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

    protected Connection establecerCon()
    {
        Connection conexion = null;
        try
        {
            Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(URL,USER ,PASSWORD );
            System.out.println("Conexion exitosa");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("error al cargar la conexion");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("Error en la conexion");
            e.printStackTrace();
        }
        return conexion;
    }
    ActionListener registrar = new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent e) 
            {
                if (jtf_usuario_sup.equals(""))
                {
                    jlb_msg_sup.setText("Debe de ingresar el nombre de usuario");    
                }
                else if (jtf_nombre_sup.equals(""))
                {
                    jlb_msg_sup.setText("Debe de ingresar su nombre");    
                }
                else if (jtf_apellido_sup.equals(""))
                {
                    jlb_msg_sup.setText("Debe de ingresar su apellido");    
                }
                else if (jtf_numero_sup.equals(""))
                {
                    jlb_msg_sup.setText("Debe de ingresar su numero de telefono");    
                }
                else if (jtf_email_sup.equals(""))
                {
                    jlb_msg_sup.setText("Debe de ingresar su corre electrónico");    
                }
                else if (jpw_password_sup.getPassword().equals(""))
                {
                    jlb_msg_sup.setText("Debe de ingresar su contraseña");    
                }
                else if (jpw_passwordconf_sup.getPassword().equals(""))
                {
                    jlb_msg_sup.setText("Debe de ingresar la confirmación de la contraseña");    
                }
                else if (jpw_passwordconf_sup.getPassword() == jpw_password_sup.getPassword())
                {
                    System.out.println(jpw_passwordconf_sup.getPassword());
                    System.out.println(jpw_password_sup.getPassword());
                    jlb_msg_sup.setText("La contraseña y la confirmación de contraseña son diferentes");    
                }
                else
                {
                    registrar_datos();
                }

            }
        };


        protected String getNm_usuario()
        {
            n_usuario = jtf_usuario_sup.getText();
            return n_usuario;
        }

        protected String getNombre()
        {
            nombre = jtf_nombre_sup.getText();
            return nombre;
        }
        
        protected String getApellido()
        {
            apellido = jtf_apellido_sup.getText();
            return n_usuario;
        }
        
        protected String getNumero()
        {
            numero = jtf_numero_sup.getText();
            return numero;
        }
        
        protected String getEmail()
        {
            email = jtf_email_sup.getText();
            return email;
        }
        
        protected String getContra()
        {
            contra = jpw_password_sup.getPassword().toString();
            return contra;
        }
        
        protected String getContra_conf()
        {
            contra_conf = jpw_passwordconf_sup.getPassword().toString();
            return contra_conf;
        }
    protected frm_singup()
    {
        inicializar();
        establecerCon();
        
        jpn_singup.add(jlb_usuario_sup);
        jpn_singup.add(jlb_nombre_sup);
        jpn_singup.add(jlb_apellido_sup);
        jpn_singup.add(jlb_numero_sup);
        jpn_singup.add(jlb_email_sup);
        jpn_singup.add(jlb_password_sup);
        jpn_singup.add(jlb_passwordconf_sup);
        jpn_singup.add(jlb_registro_sup);
        jpn_singup.add(jlb_msg_sup);
        jpn_singup.add(jtf_usuario_sup);
        jpn_singup.add(jtf_nombre_sup);
        jpn_singup.add(jtf_apellido_sup);
        jpn_singup.add(jtf_numero_sup);
        jpn_singup.add(jtf_email_sup);
        jpn_singup.add(jpw_password_sup);
        jpn_singup.add(jpw_passwordconf_sup);
        jpn_singup.add(jbt_registrarse);
        
        add(jpn_singup);
        setSize(700,400);
        setVisible(true);
        setTitle("Registro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        jbt_registrarse.addActionListener(registrar);
    }
}
