
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class frm_login extends frm_singup{

    private JLabel jlb_inse_lin, jlb_usuario_lin, jlb_contra_lin, jlb_msg_lin;
    private JTextField jtf_usuario_lin;
    private JPasswordField jpf_contra_lin;
    private JButton jbt_inse_lin;
    private JPanel jpn_login;
    private JTable jtb_tabla_registros_lin;
    DefaultTableModel modelo = new DefaultTableModel();


    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/tarea4";
    private static final String USER = "root" ;
    private static final String PASSWORD ="freddy04122002";

        private void labels()
        {
            jlb_inse_lin = new JLabel("Inicio de sesión!");
            jlb_inse_lin.setBounds(20, 30, 200, 20);

            jlb_usuario_lin = new JLabel("Usuario:");
            jlb_usuario_lin.setBounds(40, 80, 180, 20);

            jlb_contra_lin = new JLabel("Contraseña:");            
            jlb_contra_lin.setBounds(40, 120, 80, 20);

            jlb_msg_lin = new JLabel();
            jlb_msg_lin.setBounds(100, 180, 150, 20);
        }

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

        private void textfield()
        {
            jtf_usuario_lin = new JTextField();
            jtf_usuario_lin.setBounds(120, 80, 180, 20);
        }

        private void password()
        {
            jpf_contra_lin = new JPasswordField();
            jpf_contra_lin.setBounds(120, 120, 180, 20);
        }

        private void buttom()
        {
            jbt_inse_lin = new JButton("Iniciar sesión");
            jbt_inse_lin.setBounds(200, 180, 150, 20);
        }

        private void panel()
        {
            jpn_login = new JPanel();
            jpn_login.setLayout(null);
        }

        private void incializar()
        {
            labels();
            textfield();
            tablas();
            password();
            buttom();
            panel();
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

        private void consulta()
        {
            frm_login conexion = new frm_login();
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
            
/* 
        private void mostrar_tabla()
        {
            frm_login conexion = new frm_login();
            Connection cn = null;
            Statement stm = null;
            ResultSet rs = null;
            String [] datos = new String[5];

            try 
            {
                cn = conexion.establecerCon();
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
 */
        ActionListener iniciarsecion = new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) 
            {
                establecerCon();
                frm_login conexion = new frm_login();
                Connection cn = null;
                Statement stm = null;
                ResultSet rs = null;
                String nm_usuario;
                String contra;

                try 
                {
                    cn = conexion.establecerCon();
                    stm = cn.createStatement();
                    rs = stm.executeQuery("SELECT nm_usuario, password FROM usuarios WHERE nm_usuario = '"+jtf_usuario_lin.getText()+"';");

                    nm_usuario = rs.getString(1);
                    contra = rs.getString(2);

                    if(jtf_usuario_lin.equals(""))
                    {
                        jlb_msg_lin.setText("Debe ingresar su usuario y contraseña, si no está registrado debe registrarse");
                    }

                    if (jtf_usuario_lin.getText()!= nm_usuario || jpf_contra_lin.getPassword().toString() != contra)
                    {
                        jlb_msg_lin.setText("El usuario no coincide con la contraseña o viceversa");
                    }
                    else
                    {
                        consulta();
                    }
                } 
                catch (Exception ea) 
                {
                    ea.printStackTrace();
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
                    catch (SQLException ef)
                    {
                        ef.printStackTrace();
                    }
                }
            }
        };


        frm_login()
        {
            incializar();
            establecerCon();
            
            jpn_login.add(jlb_inse_lin);
            jpn_login.add(jlb_usuario_lin);
            jpn_login.add(jlb_contra_lin);
            jpn_login.add(jtf_usuario_lin);
            jpn_login.add(jpf_contra_lin);
            jpn_login.add(jbt_inse_lin);
            
            add(jpn_login);
            setSize(400,300);
            setVisible(true);
            setTitle("Inicio de sesión");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            jbt_inse_lin.addActionListener(iniciarsecion);
            
        }
}