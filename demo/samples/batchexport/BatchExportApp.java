/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class BatchExportApp
{


	/**
	 *
	 */
	private static final String TASK_FILL = "fill";
	private static final String TASK_PDF = "pdf";
	private static final String TASK_HTML = "html";
	private static final String TASK_RTF = "rtf";
	private static final String TASK_XLS = "xls";
	private static final String TASK_JXL = "jxl";
	private static final String TASK_CSV = "csv";
	private static final String TASK_ODT = "odt";
	private static final String TASK_ODS = "ods";
	private static final String TASK_DOCX = "docx";
	private static final String TASK_XHTML = "xhtml";
	
	
	/**
	 *
	 */
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			usage();
			return;
		}
				
		String taskName = args[0];
		String fileName = args[1];

		try
		{
			long start = System.currentTimeMillis();
			if (TASK_FILL.equals(taskName))
			{
				JasperFillManager.fillReportToFile(
					new File(new File(fileName), "Report1.jasper").getAbsolutePath(),
					null, 
					new JREmptyDataSource(2)
					);
				JasperFillManager.fillReportToFile(
					new File(new File(fileName), "Report2.jasper").getAbsolutePath(),
					null, 
					new JREmptyDataSource(2)
					);
				JasperFillManager.fillReportToFile(
					new File(new File(fileName), "Report3.jasper").getAbsolutePath(),
					null, 
					new JREmptyDataSource(2)
					);
				System.err.println("Filling time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_PDF.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JRPdfExporter exporter = new JRPdfExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
				
				exporter.exportReport();
				
				System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_HTML.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JRHtmlExporter exporter = new JRHtmlExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				
				exporter.exportReport();
				
				System.err.println("HTML creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_RTF.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JRRtfExporter exporter = new JRRtfExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				
				exporter.exportReport();

				System.err.println("RTF creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_XLS.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JRXlsExporter exporter = new JRXlsExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				
				exporter.exportReport();

				System.err.println("XLS creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_JXL.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JExcelApiExporter exporter = new JExcelApiExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				
				exporter.exportReport();

				System.err.println("XLS creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_CSV.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JRCsvExporter exporter = new JRCsvExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				
				exporter.exportReport();

				System.err.println("CSV creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_ODT.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JROdtExporter exporter = new JROdtExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				
				exporter.exportReport();

				System.err.println("ODT creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_ODS.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JROdsExporter exporter = new JROdsExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				
				exporter.exportReport();

				System.err.println("ODS creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_DOCX.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JRDocxExporter exporter = new JRDocxExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				
				exporter.exportReport();

				System.err.println("DOCX creation time : " + (System.currentTimeMillis() - start));
			}
			else if (TASK_XHTML.equals(taskName))
			{
				List jasperPrintList = new ArrayList();
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report1.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report2.jrprint"));
				jasperPrintList.add(JRLoader.loadObjectFromLocation("Report3.jrprint"));
				
				JRXhtmlExporter exporter = new JRXhtmlExporter();
				
				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
				
				exporter.exportReport();

				System.err.println("XHTML creation time : " + (System.currentTimeMillis() - start));
			}
			else
			{
				usage();
			}
		}
		catch (JRException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 *
	 */
	private static void usage()
	{
		System.out.println( "BatchExportApp usage:" );
		System.out.println( "\tjava BatchExportApp task file" );
		System.out.println( "\tTasks : fill | pdf | html | rtf | xls | jxl | csv | odt | ods | docx | xhtml" );
	}


}
