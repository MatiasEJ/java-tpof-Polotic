/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import servlet.clientes.SvClientesCrear;

/**
 *
 * @author keta
 */
public final class Utilidades {

	private static final String PATRON_FECHA = "yyyy-MM-dd";

	public static Date convertirFecha(String fecha) {
		try {
			return new SimpleDateFormat(PATRON_FECHA).parse(fecha);
		} catch (ParseException ex) {
			Logger.getLogger(SvClientesCrear.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public static int cantidadDiasEstadia(String fechaIn, String fechaOut) {
		try {
			int diffDays = 0;
			SimpleDateFormat dates = new SimpleDateFormat(PATRON_FECHA);
			Date startDate = dates.parse(fechaIn);
			Date endDate = dates.parse(fechaOut);
			long diff = endDate.getTime() - startDate.getTime();
			diffDays = (int) (diff / (24 * 60 * 60 * 1000));
			return Math.abs(diffDays);
		} catch (ParseException ex) {
			Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
	}

	public static boolean isDateBetween(Date in, Date out, Date fecha) {
		return !(fecha.after(in)) && !(fecha.before(out));
	}
//Metodo usado para obtener la fecha actual
	//@return Retorna un STRING con la fecha actual formato "dd-MM-yyyy"

	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat(PATRON_FECHA); //acá pueden cambiar el formato si quieren
		return formatoFecha.format(ahora);
	}

	//Metodo usado para obtener la hora actual del sistema
	//@return Retorna un STRING con la hora actual formato "hh:mm"
	public static String getHoraActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("hh:mm");
		return formateador.format(ahora);
	}

	//Convierte un String a un tipo DATE en formato dd-MM-yyyy
	//@return Retorna la fecha en formato Date
	public static synchronized Date deStringToDateInput(String fecha) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); //formato guión
		Date fechaEnviar = null;
		try {
			fechaEnviar = df.parse(fecha);
		} catch (ParseException ex) {
			Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
		}
		return fechaEnviar;
	}

	//Convierte un String a un tipo DATE en formato dd/MM/yyyy
	//@return Retorna la fecha en formato Date
	public static synchronized Date deStringToDate2(String fecha) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy"); //formato barra
		Date fechaEnviar = null;
		try {
			fechaEnviar = formatoDelTexto.parse(fecha);
			return fechaEnviar;
		} catch (ParseException ex) {
			return null;
		}
	}

	//Convertir Date a String
	public static String dateAString(Date fecha) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat(PATRON_FECHA);
		return formatoFecha.format(fecha);
	}
	public static String dateAStringInput(Date fecha) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		return formatoFecha.format(fecha);
	}

	//Convertir Hora a String
	public static Date convertirHoraStringADate(String hora, String minutos) throws ParseException {
		String horaString = hora + ":" + minutos;
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		Date horaCompleta = new Date();
		horaCompleta = formatoHora.parse(horaString);
		System.out.println("La hora es: " + horaCompleta);
		return horaCompleta;
	}

	public static boolean overlap(Date start1, Date end1, Date start2, Date end2) {
		 return !(start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime()); 
	}

	public static TipoHabitacion TipoHabitacion(String tipoHabitacion) {
		for (TipoHabitacion tipoHab : TipoHabitacion.values()) {
			if (tipoHab.toString().equals(tipoHabitacion)) {
				return tipoHab;
			}
		}
		return null;
	}


}
