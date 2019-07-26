import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import Food.Food;


class LoginObj extends JFrame
{
	public LoginObj(String title)
	{
		super(title);
	}
	
	
	JLabel user_name,password;
	JTextField user_name_txt;
	JPasswordField password_txt;
	JButton login;
	
	void components()
	{
	
	user_name = new JLabel("User Name");
	user_name.setForeground(new Color(255,255,255));
	user_name.setFont(new Font("Courier New",Font.ITALIC + Font.BOLD, 16));
	
	
	password = new JLabel("Password");
	password.setForeground(new Color(255,255,255));
	password.setFont(new Font("Courier New",Font.BOLD + Font.ITALIC, 16));
	
	user_name_txt = new JTextField();
	password_txt = new JPasswordField();
	
	login = new JButton("Login");
	login.setForeground(new Color(214, 44, 53));
	login.setBackground(new Color(0,0,0));
	login.setFont(new Font("Helvetica", Font.BOLD + Font.ITALIC, 12));
	
	setLayout(null);
	
	user_name.setBounds(500,300,100,20);
	password.setBounds(500,350,100,20);
	user_name_txt.setBounds(700,300,100,20);
	password_txt.setBounds(700,350,100,20);
	login.setBounds(700,400,100,20);
	
	
	add(user_name);
	add(password);
	add(user_name_txt);
	add(password_txt);
	add(login);
	
	
	getContentPane().setBackground(new Color(255, 61, 71));
	
	login.addActionListener(new Handler());
	
	}
	
	
	public class Handler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","javadb","abcd");
				String sql = "select * from login where user_name = '" + user_name_txt.getText() + "'and password = '" + password_txt.getText() + "'";
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql);
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null,"login successful");
					conn.close();
					setVisible(false);
					Food obj = new Food();
					obj.setExtendedState(JFrame.MAXIMIZED_BOTH);
					obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					obj.components();
					obj.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid username or password");
				}
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
	}
}




public class Login
{
	public static void main(String args[])
	{
		LoginObj obj = new LoginObj("Login");
		
		obj.setExtendedState(JFrame.MAXIMIZED_BOTH);
		obj.components();
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			
		}

	}
}
