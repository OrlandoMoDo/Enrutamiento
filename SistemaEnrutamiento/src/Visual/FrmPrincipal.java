package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Enrutamiento;
import Logico.Red;
import Logico.Router;
import Logico.SistemaEnrutamiento;

import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	//Tamaño de la topologia:
	private int topologyWidht = 350;
	private int topologyHeigh = 350;

	private Image imagenTopologia = new ImageIcon(FrmPrincipal.class.getResource("Imagenes/Topologia.png")).getImage().getScaledInstance(topologyWidht, topologyHeigh, Image.SCALE_SMOOTH);
	private JTextField txtRedB;
	private JLabel lblRedB;
	private JTextField txtMaskF;
	private JTextField txtMaskG;
	private JTextField txtRedA;
	private JTextField txtMaskA;
	private JTextField txtMaskB;
	private JTextField txtRedC;
	private JTextField txtMaskC;
	private JTextField txtRedD;
	private JTextField txtMaskD;
	private JTextField txtRedG;
	private JTextField txtRedF;
	private JTextField txtRedE;
	private JTextField txtMaskE;
	private JButton btnComprobar;
	private JButton btnNuevo;
	private JButton btnSalir;
	private JLabel lblTopologia_1;
	private JPanel pnTopologia;
	private JLabel lblIpGatewayRedC;
	private JLabel lblIpGatewayRedA;
	private JLabel lblIpGatewayRedD;
	private JLabel lblIpGatewayRedG;
	private JButton btnSiguiente;
	private boolean validarA;
	private boolean validarB;
	private boolean validarC;
	private boolean validarD;
	private boolean validarE;
	private boolean validarF;
	private boolean validarG;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Sistema de Redes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension=getToolkit().getScreenSize();
		setBounds(100, 100, dimension.width-400, dimension.height-120);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnBody = new JPanel();
		pnBody.setBackground(Color.LIGHT_GRAY);
		contentPane.add(pnBody, BorderLayout.CENTER);
		pnBody.setLayout(null);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarA=SistemaEnrutamiento.getInstance().validarIP(txtRedA.getText(), txtMaskA.getText());
				validarB=SistemaEnrutamiento.getInstance().validarIP(txtRedB.getText(), txtMaskB.getText());
				validarC=SistemaEnrutamiento.getInstance().validarIP(txtRedC.getText(), txtMaskC.getText());
				validarD=SistemaEnrutamiento.getInstance().validarIP(txtRedD.getText(), txtMaskD.getText());
				validarE=SistemaEnrutamiento.getInstance().validarIP(txtRedE.getText(), txtMaskE.getText());
				validarF=SistemaEnrutamiento.getInstance().validarIP(txtRedF.getText(), txtMaskF.getText());
				validarG=SistemaEnrutamiento.getInstance().validarIP(txtRedG.getText(), txtMaskG.getText());
				if(camposLlenos()) {
					if(!(txtRedA.getText().equals(txtRedB.getText())||txtRedA.getText().equals(txtRedC.getText())||txtRedA.getText().equals(txtRedD.getText())||txtRedA.getText().equals(txtRedE.getText())||txtRedA.getText().equals(txtRedF.getText())
							||txtRedA.getText().equals(txtRedG.getText())||txtRedB.getText().equals(txtRedC.getText())||txtRedB.getText().equals(txtRedD.getText())||txtRedB.getText().equals(txtRedE.getText())||txtRedB.getText().equals(txtRedF.getText())
							||txtRedB.getText().equals(txtRedG.getText())||txtRedC.getText().equals(txtRedD.getText())||txtRedC.getText().equals(txtRedE.getText())||txtRedC.getText().equals(txtRedF.getText())
							||txtRedC.getText().equals(txtRedG.getText())||txtRedD.getText().equals(txtRedE.getText())||txtRedD.getText().equals(txtRedF.getText())||txtRedD.getText().equals(txtRedG.getText())
							||txtRedE.getText().equals(txtRedF.getText())||txtRedE.getText().equals(txtRedG.getText())||txtRedF.getText().equals(txtRedG.getText()))) {
						if(validarA&&validarB&&validarC&&validarD&&validarE&&validarF&&validarG) {
							Router routerIngresar = new Router("R-2");
							for(int i=0; i<4; i++) {
								int ip;
								switch (i) {
								case 0:
									ip = calcularIpGateway(txtRedA);
									lblIpGatewayRedA.setText("."+ip);
									lblIpGatewayRedA.setVisible(true);
									routerIngresar.ingresarNextHop("."+ip);
									routerIngresar.ingresarInterfaces("e0/0");
									break;
									
								case 1:
									ip = calcularIpGateway(txtRedC);
									lblIpGatewayRedC.setText("."+ip);
									lblIpGatewayRedC.setVisible(true);
									routerIngresar.ingresarNextHop("."+ip);
									routerIngresar.ingresarInterfaces("e0/2");
									break;
								
								case 2:
									ip = calcularIpGateway(txtRedD);
									lblIpGatewayRedD.setText("."+ip);
									lblIpGatewayRedD.setVisible(true);
									routerIngresar.ingresarNextHop("."+ip);
									routerIngresar.ingresarInterfaces("e0/3");
									break;
								
								case 3:
									ip = calcularIpGateway(txtRedG);
									lblIpGatewayRedG.setText("."+ip);
									lblIpGatewayRedG.setVisible(true);
									routerIngresar.ingresarNextHop("."+ip);
									routerIngresar.ingresarInterfaces("e0/1");
									break;
								}
							}
							txtRedA.setText(txtRedA.getText()+'.');
							txtRedB.setText(txtRedB.getText()+'.');
							txtRedC.setText(txtRedC.getText()+'.');
							txtRedD.setText(txtRedD.getText()+'.');
							txtRedE.setText(txtRedE.getText()+'.');
							txtRedF.setText(txtRedF.getText()+'.');
							txtRedG.setText(txtRedG.getText()+'.');
							btnSiguiente.setEnabled(true);
							ArrayList<Integer> octetos = new ArrayList<Integer>();
							char[] chars = txtRedA.getText().toCharArray();
							int mask;
							for(int i=0; i<7; i++) {
								mask=0;
								String octeto = "";
								octetos.clear();
		
								switch (i) {
									case 0: chars = txtRedA.getText().toCharArray(); mask=Integer.parseInt(txtMaskA.getText()); break;
									case 1: chars = txtRedB.getText().toCharArray(); mask=Integer.parseInt(txtMaskB.getText()); break;
									case 2: chars = txtRedC.getText().toCharArray(); mask=Integer.parseInt(txtMaskC.getText()); break;
									case 3: chars = txtRedD.getText().toCharArray(); mask=Integer.parseInt(txtMaskD.getText()); break;
									case 4: chars = txtRedE.getText().toCharArray(); mask=Integer.parseInt(txtMaskE.getText()); break;
									case 5: chars = txtRedF.getText().toCharArray(); mask=Integer.parseInt(txtMaskF.getText()); break;
									case 6: chars = txtRedG.getText().toCharArray(); mask=Integer.parseInt(txtMaskG.getText()); break;
								}
								for(char ch: chars) {
									if((ch!='.')) {
										octeto = octeto+ch;
									}else {
										int octetoInt = Integer.parseInt(octeto);
										octetos.add(octetoInt);
										octeto="";
									}
								}
								
								Red redIngresar = new Red(octetos.get(0), octetos.get(1), octetos.get(2), octetos.get(3), mask);
								switch (i) {
									case 0: routerIngresar.ingresarRed(redIngresar); break;
									case 2: routerIngresar.ingresarRed(redIngresar); break;
									case 3: routerIngresar.ingresarRed(redIngresar); break;
									case 6: routerIngresar.ingresarRed(redIngresar); break;
								}
								SistemaEnrutamiento.getInstance().ingresarRed(redIngresar);
							}
							SistemaEnrutamiento.getInstance().ingresarRouter(routerIngresar);
						}else {
							System.out.println("La IP no es valida");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Llene todos los campos");
				}
			}


		});
		btnComprobar.setBounds(233, 491, 122, 39);
		pnBody.add(btnComprobar);
		
		pnTopologia = new JPanel();
		pnTopologia.setBackground(Color.WHITE);
		pnTopologia.setBounds(64, 69, 695, 398);
		pnBody.add(pnTopologia);
		pnTopologia.setLayout(null);
		
		lblIpGatewayRedG = new JLabel(".128");
		lblIpGatewayRedG.setBounds(396, 294, 69, 20);
		pnTopologia.add(lblIpGatewayRedG);
		
		lblIpGatewayRedD = new JLabel(".128");
		lblIpGatewayRedD.setBounds(182, 317, 69, 20);
		pnTopologia.add(lblIpGatewayRedD);
		
		lblIpGatewayRedA = new JLabel(".128");
		lblIpGatewayRedA.setBounds(231, 43, 69, 20);
		pnTopologia.add(lblIpGatewayRedA);
		
		lblIpGatewayRedC = new JLabel(".128");
		lblIpGatewayRedC.setBounds(396, 156, 69, 20);
		pnTopologia.add(lblIpGatewayRedC);
		
		txtRedG = new JTextField();
		txtRedG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c)&&(c!='.'))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtRedG.setColumns(10);
		txtRedG.setBounds(288, 226, 109, 26);
		pnTopologia.add(txtRedG);
		
		JLabel lblRedG = new JLabel("Red G:");
		lblRedG.setBounds(310, 204, 69, 20);
		pnTopologia.add(lblRedG);
		
		JLabel label_7 = new JLabel("/");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_7.setBounds(396, 229, 14, 20);
		pnTopologia.add(label_7);
		
		txtMaskG = new JTextField();
		txtMaskG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtMaskG.setColumns(10);
		txtMaskG.setBounds(406, 226, 33, 26);
		pnTopologia.add(txtMaskG);
		
		JLabel label_6 = new JLabel("/");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_6.setBounds(574, 272, 14, 20);
		pnTopologia.add(label_6);
		
		JLabel lblRedF = new JLabel("Red F:");
		lblRedF.setBounds(495, 250, 69, 20);
		pnTopologia.add(lblRedF);
		
		txtRedF = new JTextField();
		txtRedF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c)&&(c!='.'))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtRedF.setColumns(10);
		txtRedF.setBounds(465, 270, 109, 26);
		pnTopologia.add(txtRedF);
		
		txtMaskF = new JTextField();
		txtMaskF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtMaskF.setColumns(10);
		txtMaskF.setBounds(584, 270, 33, 26);
		pnTopologia.add(txtMaskF);
		
		txtMaskE = new JTextField();
		txtMaskE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtMaskE.setColumns(10);
		txtMaskE.setBounds(363, 370, 33, 26);
		pnTopologia.add(txtMaskE);
		
		JLabel lblRedE = new JLabel("Red E:");
		lblRedE.setBounds(274, 350, 69, 20);
		pnTopologia.add(lblRedE);
		
		JLabel label_5 = new JLabel("/");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_5.setBounds(353, 372, 14, 20);
		pnTopologia.add(label_5);
		
		txtRedE = new JTextField();
		txtRedE.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c)&&(c!='.'))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtRedE.setColumns(10);
		txtRedE.setBounds(244, 370, 109, 26);
		pnTopologia.add(txtRedE);
		
		txtMaskD = new JTextField();
		txtMaskD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtMaskD.setColumns(10);
		txtMaskD.setBounds(121, 272, 33, 26);
		pnTopologia.add(txtMaskD);
		
		JLabel label_2 = new JLabel("/");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_2.setBounds(111, 275, 14, 20);
		pnTopologia.add(label_2);
		
		JLabel lblRedD = new JLabel("Red D:");
		lblRedD.setBounds(32, 250, 69, 20);
		pnTopologia.add(lblRedD);
		
		txtRedD = new JTextField();
		txtRedD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c)&&(c!='.'))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtRedD.setColumns(10);
		txtRedD.setBounds(2, 272, 109, 26);
		pnTopologia.add(txtRedD);
		
		JLabel label_3 = new JLabel("/");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_3.setBounds(355, 154, 14, 20);
		pnTopologia.add(label_3);
		
		JLabel lblRedC = new JLabel("Red C:");
		lblRedC.setBounds(276, 129, 69, 20);
		pnTopologia.add(lblRedC);
		
		txtMaskC = new JTextField();
		txtMaskC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtMaskC.setColumns(10);
		txtMaskC.setBounds(365, 151, 33, 26);
		pnTopologia.add(txtMaskC);
		
		txtRedC = new JTextField();
		txtRedC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c)&&(c!='.'))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtRedC.setColumns(10);
		txtRedC.setBounds(244, 151, 109, 26);
		pnTopologia.add(txtRedC);
		
		txtMaskB = new JTextField();
		txtMaskB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtMaskB.setColumns(10);
		txtMaskB.setBounds(516, 77, 33, 26);
		pnTopologia.add(txtMaskB);
		
		txtRedB = new JTextField();
		txtRedB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c)&&(c!='.'))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtRedB.setColumns(10);
		txtRedB.setBounds(395, 77, 109, 26);
		pnTopologia.add(txtRedB);
		
		JLabel label_1 = new JLabel("/");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(506, 80, 14, 20);
		pnTopologia.add(label_1);
		
		lblRedB = new JLabel("Red B:");
		lblRedB.setBounds(427, 55, 69, 20);
		pnTopologia.add(lblRedB);
		
		JLabel label = new JLabel("/");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(182, 80, 14, 20);
		pnTopologia.add(label);
		
		txtMaskA = new JTextField();
		txtMaskA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtMaskA.setColumns(10);
		txtMaskA.setBounds(192, 77, 33, 26);
		pnTopologia.add(txtMaskA);
		
		JLabel lblRedA = new JLabel("Red A:");
		lblRedA.setBounds(103, 55, 69, 20);
		pnTopologia.add(lblRedA);
		
		txtRedA = new JTextField();
		txtRedA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!Character.isDigit(c)&&(c!='.'))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		txtRedA.setBounds(71, 77, 109, 26);
		pnTopologia.add(txtRedA);
		txtRedA.setColumns(10);
		
		JLabel lblTopologia = new JLabel("");
		lblTopologia.setBounds(136, 32, topologyWidht, topologyHeigh);
		lblTopologia.setIcon(new ImageIcon(imagenTopologia));
		//lblTopologia.setBackground((Color.BLACK));
		pnTopologia.add(lblTopologia);
		
		lblTopologia_1 = new JLabel("Topologia:");
		lblTopologia_1.setBounds(280, 10, 233, 20);
		pnTopologia.add(lblTopologia_1);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcion = 0;
				if(!txtRedA.getText().equalsIgnoreCase("")) {
					opcion = JOptionPane.showConfirmDialog(null, "Esta seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
				}
				if(opcion==0) {
					inicio();
					btnComprobar.setEnabled(true);
					txtRedA.setEnabled(true);
					txtMaskA.setEnabled(true);
					txtRedB.setEnabled(true);
					txtMaskB.setEnabled(true);
					txtRedC.setEnabled(true);
					txtMaskC.setEnabled(true);
					txtRedD.setEnabled(true);
					txtMaskD.setEnabled(true);
					txtRedE.setEnabled(true);
					txtMaskE.setEnabled(true);
					txtRedF.setEnabled(true);
					txtMaskF.setEnabled(true);
					txtRedG.setEnabled(true);
					txtMaskG.setEnabled(true);
				}
			}
		});
		btnNuevo.setBounds(64, 491, 122, 39);
		pnBody.add(btnNuevo);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(637, 491, 122, 39);
		pnBody.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("Sistema para calcular la mejor ruta");
		lblNewLabel.setBounds(257, 33, 233, 20);
		pnBody.add(lblNewLabel);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				FrmEnrutamiento frmAux = new FrmEnrutamiento();
				frmAux.setVisible(true);
				//pnTopologia
			}
		});
		btnSiguiente.setEnabled(false);
		btnSiguiente.setBounds(431, 491, 122, 39);
		pnBody.add(btnSiguiente);
		
		inicio();
		
		
	}

	private void inicio() {
		
		btnComprobar.setEnabled(false);
		lblIpGatewayRedA.setVisible(false);
		lblIpGatewayRedC.setVisible(false);
		lblIpGatewayRedD.setVisible(false);
		lblIpGatewayRedG.setVisible(false);
		
		txtRedA.setText("");
		txtRedA.setEnabled(false);
		txtMaskA.setText("");
		txtMaskA.setEnabled(false);
		
		txtRedB.setText("");
		txtRedB.setEnabled(false);
		txtMaskB.setText("");
		txtMaskB.setEnabled(false);
		
		txtRedC.setText("");
		txtRedC.setEnabled(false);
		txtMaskC.setText("");
		txtMaskC.setEnabled(false);
		
		txtRedD.setText("");
		txtRedD.setEnabled(false);
		txtMaskD.setText("");
		txtMaskD.setEnabled(false);
		
		txtRedE.setText("");
		txtRedE.setEnabled(false);
		txtMaskE.setText("");
		txtMaskE.setEnabled(false);
		
		txtRedF.setText("");
		txtRedF.setEnabled(false);
		txtMaskF.setText("");
		txtMaskF.setEnabled(false);
		
		txtRedG.setText("");
		txtRedG.setEnabled(false);
		txtMaskG.setText("");
		txtMaskG.setEnabled(false);
	}
	
	private boolean camposLlenos() {
		boolean aux = true;
		if( txtRedA.getText().equalsIgnoreCase("") || txtMaskA.getText().equalsIgnoreCase("")
				|| txtRedB.getText().equalsIgnoreCase("") || txtMaskB.getText().equalsIgnoreCase("")
				|| txtRedC.getText().equalsIgnoreCase("") || txtMaskC.getText().equalsIgnoreCase("")
				|| txtRedD.getText().equalsIgnoreCase("") || txtMaskD.getText().equalsIgnoreCase("")
				|| txtRedE.getText().equalsIgnoreCase("") || txtMaskE.getText().equalsIgnoreCase("")
				|| txtRedF.getText().equalsIgnoreCase("") || txtMaskF.getText().equalsIgnoreCase("")
				|| txtRedG.getText().equalsIgnoreCase("") || txtMaskG.getText().equalsIgnoreCase("")) {
			aux = false;
		}
		return aux;
	}
	
	private int calcularIpGateway(JTextField red) {
		
		int cantPuntos = 0;
		int aux = 0;
		String redCalcular = red.getText();
		String direccionIp ="";
		int direccionIpInt;
		
		char[] chars = redCalcular.toCharArray();
		for(char ch: chars) {
			if(ch=='.') {
				cantPuntos++;
			}
			if(cantPuntos>2) {
				if(aux==0) {
					aux++;
				}else {
					direccionIp = direccionIp+""+ch;
				}
			}
		}
		direccionIpInt = Integer.parseInt(direccionIp);
		direccionIpInt++;
		
		return direccionIpInt;
		
	}
}
