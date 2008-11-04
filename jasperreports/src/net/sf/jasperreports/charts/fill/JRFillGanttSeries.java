package net.sf.jasperreports.charts.fill;

import java.util.Date;

import net.sf.jasperreports.charts.JRGanttSeries;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.fill.JRCalculator;
import net.sf.jasperreports.engine.fill.JRExpressionEvalException;
import net.sf.jasperreports.engine.fill.JRFillHyperlinkHelper;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;

/**
 * @author Peter Risko (peter@risko.hu)
 * @version $Id: JRDesignTimePeriodSeries.java 1923 2007-10-25 09:44:32Z lucianc $
 */
public class JRFillGanttSeries implements JRGanttSeries {

    /**
     *
     */
    protected JRGanttSeries parent = null;

    private Comparable series = null;
    private String task = null;
    private String subtask = null;
    private Date startDate = null;
    private Date endDate = null;
    private Number percent = null;
    private String label = null;
    private JRPrintHyperlink itemHyperlink;
    
    
    /**
     *
     */
    public JRFillGanttSeries(
        JRGanttSeries ganttSeries, 
        JRFillObjectFactory factory
        )
    {
        factory.put(ganttSeries, this);

        parent = ganttSeries;
    }


    /**
     *
     */
    public JRExpression getSeriesExpression()
    {
        return parent.getSeriesExpression();
    }
        
    public JRExpression getStartDateExpression(){
        return parent.getStartDateExpression();
    }
    
    public JRExpression getEndDateExpression(){
        return parent.getEndDateExpression();
    }
    
    /**
     *
     */
    public JRExpression getTaskExpression()
    {
        return parent.getTaskExpression();
    }
        
    /**
     *
     */
    public JRExpression getSubtaskExpression()
    {
        return parent.getSubtaskExpression();
    }
        
    /**
     *
     */
    public JRExpression getPercentExpression()
    {
        return parent.getPercentExpression();
    }
       
    /**
     *
     */
    public JRExpression getLabelExpression()
    {
        return parent.getLabelExpression();
    }
    
    
    /**
     *
     */
    protected Comparable getSeries()
    {
        return series;
    }
        
    protected Date getStartDate(){
        return startDate;
    }
    
    protected Date getEndDate(){
        return endDate;
    }
    
    /**
     *
     */
    protected String getTask()
    {
        return task;
    }
        
    /**
     *
     */
    protected String getSubtask()
    {
        return subtask;
    }
        
    /**
     *
     */
    protected Number getPercent()
    {
        return percent;
    }
       
    /**
     *
     */
    protected String getLabel()
    {
        return label;
    }
    
    protected JRPrintHyperlink getPrintItemHyperlink()
    {
        return itemHyperlink;
    }
    
    
    /**
     *
     */
    protected void evaluate(JRCalculator calculator) throws JRExpressionEvalException
    {
        series = (Comparable)calculator.evaluate(getSeriesExpression()); 
        startDate = (Date)calculator.evaluate( getStartDateExpression() );
        endDate = (Date)calculator.evaluate( getEndDateExpression() );
        task = (String)calculator.evaluate(getTaskExpression()); 
        subtask = (String)calculator.evaluate(getSubtaskExpression());
        percent = (Number)calculator.evaluate(getPercentExpression());
        label = (String)calculator.evaluate(getLabelExpression());
        
        if (hasItemHyperlinks())
        {
            evaluateItemHyperlink(calculator);
        }
    }


    protected void evaluateItemHyperlink(JRCalculator calculator) throws JRExpressionEvalException
    {
        try
        {
            itemHyperlink = JRFillHyperlinkHelper.evaluateHyperlink(getItemHyperlink(), calculator, JRExpression.EVALUATION_DEFAULT);
        }
        catch (JRExpressionEvalException e)
        {
            throw e;
        }
        catch (JRException e)
        {
            throw new JRRuntimeException(e);
        }
    }


    public JRHyperlink getItemHyperlink()
    {
        return parent.getItemHyperlink();
    }

    
    public boolean hasItemHyperlinks()
    {
        return getItemHyperlink() != null;
    }


    
}
