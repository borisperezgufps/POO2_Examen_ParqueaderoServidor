package poo2.examen.servidor;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

import poo2.examen.interfaz.IParqueadero;

public class ParqueaderoServidor extends UnicastRemoteObject implements IParqueadero{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ParqueaderoServidor() throws RemoteException {
		System.out.println("Iniciando");
	}
	
	
	public static void main(String[] args) throws Exception {
		(new ParqueaderoServidor()).iniciarServidor();
	}
	
	public void iniciarServidor() {
		
		Thread t = new Thread(() -> {
			try {
				String ip = (InetAddress.getLocalHost()).toString();
				System.out.println("Escuchando en... "+ip+":3232");
				Registry reg = LocateRegistry.createRegistry(3232);
				reg.bind("server", (IParqueadero)this);
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
		
		t.start();
		
	}


	@Override
	public int consultarValorPagar(String placa) throws RemoteException {
		
		if(placa.isEmpty())
			throw new RemoteException("La placa no puede llegar vacía");
		if(placa.length()!=6)
			throw new RemoteException("La placa debe tener seis caracteres alfanumericos");
		
		return (int)((Math.random())*10000);
	}




	@Override
	public boolean pagarVehiculo(String placa) throws RemoteException {		
		return true;
	}


	@Override
	public String registrarVehiculo(String nombreParqueadero, String idParqueadero, 
			String placa, boolean aplicaDescuento) throws RemoteException {
		
		if(nombreParqueadero.isEmpty())
			throw new RemoteException("Favor diligenciar el nombre del parqueadero");
		if(idParqueadero.isEmpty())
			throw new RemoteException("Favor diligenciar el ID del parqueadero");
		if(placa.isEmpty())
			throw new RemoteException("La placa no puede llegar vacía");
		if(placa.length()!=6)
			throw new RemoteException("La placa debe tener seis caracteres alfanumericos");
		
		
		Calendar c = Calendar.getInstance();
		int hora = c.get(Calendar.HOUR_OF_DAY);
		int minuto = c.get(Calendar.MINUTE);
		
		String sHora = String.valueOf(hora);
		String sMinuto = String.valueOf(minuto);
		
		if(sHora.length()==1)
			sHora = "0"+sHora;
		if(sMinuto.length()==1)
			sMinuto = "0"+sMinuto;
		
		
		// TODO Auto-generated method stub
		return sHora + ":" + sMinuto + "14-Dic-2023";
	}
	
}
