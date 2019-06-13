package co.com.samtel.ControlAccesos.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import co.com.samtel.ControlAccesos.dto.ControlDiarioAlertaDto;
import co.com.samtel.ControlAccesos.dto.ReportControlAccesoOrd;
import co.com.samtel.ControlAccesos.entities.ControlAccesosOrd;
import co.com.samtel.ControlAccesos.entities.ControlRegistro;
import co.com.samtel.ControlAccesos.entities.ResumenMensual;



public class ControlOrdenados {

	public ControlOrdenados() {

	}



	/*
	 * metodo que me permite traer la ruta especifica del proyecto
	 */
	public void reporteControlAccesosOrd(List<ReportControlAccesoOrd> controles) {

		try {

			
			
			// llamado al metodo que me permite generar la carpeta o verificar si se
			// encuentra disponible
			folderVerify.createDirec();
			

			// llamdo al metodo que me genera el nombre del archivo a y ala vez la ruta a la
			// carpeta donde se creara el reporte
			String fileName = folderVerify.createFileName("Control Acceso Ordenado");

			// creacion del archivo de excel para el reporte
			Workbook book = new XSSFWorkbook();
			Sheet sheet = book.createSheet("Control Accesos Ordenados");
			
			

			// LLENADO DEL ARCHIVO CON EL TITULO Y CON LOS DATOS QUE ME TRAE LA BASE DE
			// DATOS.

			// Esto me permite agregarle los estilos que tomar� las celda de los titulos de
			// los reportes

			CellStyle tituloEstilo = book.createCellStyle();
			tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
			tituloEstilo.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
			Font title = book.createFont();
			title.setFontName("Calibri");
			title.setBold(true);
			title.setFontHeightInPoints((short) 11);
			tituloEstilo.setFont(title);

			// SE CREA LAS CELDAS CON EL NOMBRE ASOCIADO CON LA INFORMACI�N QUE LLEGA A
			// CONTROL ACCESO ORDENADO.
			// SE HACE UN CICLO PARA REGISTRAR EN UNA MISMA FILA LOS TITULOS DE LA
			// INFORMACI�N.
			
			// SE CREA EL TITULO BASE PARA LA HOJA NUMERO 1 CON EL NOMBRE DE 'Control Accesos Ordenados' 
			
			String[] tituloHead = new String[] { "FECHA", "CODIGO USUARIO", "NOMBRE", "TIPO ACCESO (5 entrada - 6 salida)"};
			Row titulos = sheet.createRow(0);

			for (int i = 0; i < tituloHead.length; i++) {
				Cell titulo = titulos.createCell(i);
				titulo.setCellStyle(tituloEstilo);
				titulo.setCellValue(tituloHead[i]);
				sheet.autoSizeColumn(i);

			}

			// Carga de los datos a el archivo excel.

			int numCol = 4;
			int count = 0;
			double progreso = 0;
			LocalDateTime date = LocalDateTime.now();
            System.out.println("Procesando lectura y escritura");
            System.out.println(date);
			for (ReportControlAccesoOrd controlD : controles) {

				count++;
				progreso = (double) ((count*1.0/controles.size())*100);
				System.out.println("Progreso: "+progreso+"%-"+count+" "+controles.size());
				if (count <= controles.size()) {
					Row controlesD = sheet.createRow(count);

					for (int i = 0; i < numCol; i++) {

						Cell cellData = controlesD.createCell(i);
						switch (i) {

						case 0:
							cellData.setCellValue(controlD.getFecha().toString());
							
							break;
						case 1:
							cellData.setCellValue( controlD.getCodigoUsuario());
							
							break;
						case 2:
							cellData.setCellValue(controlD.getNombre());
							
							break;
						case 3:
							cellData.setCellValue(controlD.getTipoAcceso());
							
							break;
						
						}
						sheet.autoSizeColumn(i);
					}

				}

			}
			date = LocalDateTime.now();
			System.out.println("Coronamos"+date);
			FileOutputStream fileout = new FileOutputStream(new File(fileName));
			book.write(fileout);
			fileout.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("El arhivo esta abierto cierrelo e intente nuevamente");
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
