package math.model;

import java.util.ArrayList;
import java.util.List;

public class GCodeModel {


	private List<GCodeLayer> layers;

	public GCodeModel() {
		this.layers = new ArrayList<GCodeLayer>();
	}

	public List<GCodeLayer> getLayers() {
		return layers;
	}

	
	public GCodeLayer addLayer(double z){
		GCodeLayer layer = new GCodeLayer(this, z);
		this.layers.add(layer);
		return layer;
	}
	
	
	public void removeLayer(GCodeLayer layer){
		this.layers.remove(layer);
	}
	
	public String toString() {
		// TODO: change for to for each
		
		String opis = " ";
		for (GCodeLayer layer : layers) {
			opis = layer.toString();
//			for(int i=0; i<2; i++){
//			Double x1 = layer.getEdges().get(i).getV1().getX();
//			opis = x1.toString() + " " + layers.size();
//			}
		}

		
		return "Warstwa " + layers.size() + " " + opis;
	}

}

