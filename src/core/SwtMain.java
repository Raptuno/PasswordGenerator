package core;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Group;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.MouseEvent;

public class SwtMain {
	Clipboard cb=new Clipboard(Display.getDefault());
	String store=new String();

	protected Shell shlSwtMain;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwtMain window = new SwtMain();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSwtMain.open();
		shlSwtMain.layout();
		while (!shlSwtMain.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		//JOptionPane.showMessageDialog(null, "No cierre el programa al momento de generar la contraseña", "ADVERTENCIA: Limitación de SWT", 2);
		
		PassEngine pe=new PassEngine();
		
		shlSwtMain = new Shell();
		shlSwtMain.setSize(211, 150);
		shlSwtMain.setText("PassGen");
		FormLayout fl_shlSwtMain = new FormLayout();
		fl_shlSwtMain.marginBottom = 10;
		fl_shlSwtMain.marginRight = 10;
		shlSwtMain.setLayout(fl_shlSwtMain);
		
		Group grpOpciones = new Group(shlSwtMain, SWT.NONE);
		grpOpciones.setText("Opciones");
		grpOpciones.setLayout(new GridLayout(1, false));
		FormData fd_grpOpciones = new FormData();
		fd_grpOpciones.top = new FormAttachment(0, 10);
		fd_grpOpciones.left = new FormAttachment(0, 10);
		grpOpciones.setLayoutData(fd_grpOpciones);
		
		Composite cmtLength = new Composite(grpOpciones, SWT.NONE);
		cmtLength.setLayout(new GridLayout(2, false));
		
		Label lblLongitudDeseada = new Label(cmtLength, SWT.NONE);
		lblLongitudDeseada.setSize(97, 15);
		lblLongitudDeseada.setText("Longitud deseada:");
		
		Spinner spnLength = new Spinner(cmtLength, SWT.BORDER);
		spnLength.addMouseWheelListener(new MouseWheelListener() {
			public void mouseScrolled(MouseEvent arg0) {
				int scroll=arg0.count/3;
				spnLength.setSelection(spnLength.getSelection()+(spnLength.getIncrement()*scroll));
			}
		});
		spnLength.setSize(41, 22);
		spnLength.setMaximum(128);
		spnLength.setMinimum(16);
		
		Composite cmtBotones = new Composite(shlSwtMain, SWT.NONE);
		cmtBotones.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_cmtBotones = new FormData();
		fd_cmtBotones.top = new FormAttachment(grpOpciones, 6);
		fd_cmtBotones.left = new FormAttachment(0, 10);
		cmtBotones.setLayoutData(fd_cmtBotones);
		
		Button btnGenerar = new Button(cmtBotones, SWT.NONE);
		btnGenerar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				store=pe.PassGen(spnLength.getSelection());
				TextTransfer tt=TextTransfer.getInstance();
				cb.setContents(new Object[] {store}, new Transfer[] {tt});
				JOptionPane.showMessageDialog(null, "Contraseña copiada al portapapeles");
			}
		});
		btnGenerar.setText("Generar");
		
		Button btnSalir = new Button(cmtBotones, SWT.NONE);
		btnSalir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int exitConfirm=JOptionPane.showInternalConfirmDialog(null, "¿Está seguro de que desea salir? \nLa contaseña copiada se borrará del portapapeles");
				if (exitConfirm==0) {
					Runtime.getRuntime().exit(0);
				}
			}
		});
		btnSalir.setText("Salir");

	}
}
