package agiw.agiw2019;

import java.io.*;
import java.util.*;//Scanner is included

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.*;
import org.jfree.data.*;



public class Plot_1
{

	static Scanner scanner;
	static String[] key;
	static double[] series;
	//static File file;

	public static void main(String[] args)
	{


		plotResult();

	}


	public static void plotResult() {

		//Read in the file "calculation.txt" using Scanner and call hasNextDouble on it
		Map<String , List<Double>> mappa = new HashMap<String, List<Double>>();
		List<Double> lista;
		try {
			//int index = 0;

			File file = new File("/home/ebt/agiwvic/tags.txt");
			
			series = new double[2000];
			key= new String[2000];
			scanner = new Scanner(file);
			int i=0;
			// Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				key[i]=scanner.next().toString();
				series[i]=scanner.nextDouble();
				
				if (mappa.containsKey(key[i])) {
					lista=  mappa.get(key[i]);
					lista.add(series[i]);
					mappa.put(key[i], lista);
					
					
				}
				System.out.println(series[i]);
				System.out.println(key[i]);
				i++;
			}
			scanner.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		HistogramDataset xyDataset = new HistogramDataset();
		xyDataset.addSeries("Agiw",series ,200);


		JFreeChart chart = ChartFactory.createHistogram("Amazon links tag html count", "n tags", "occurencies",xyDataset, PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame1=new ChartFrame("XYLine Chart",chart);
		frame1.setVisible(true);
		frame1.setSize(1076,20);



	}//End of method




}//end of class

