package app.model;

import app.entities.Tender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TenderModel implements Model<Integer, Tender>{

    private TreeMap<Integer, Tender> tenderModel;

    private TenderModel() {
        tenderModel = new TreeMap<>();
    }

    private static TenderModel instance = new TenderModel();

    public static TenderModel getInstance() {
        return instance;
    }

    public void add(Integer key, Tender tender) {
        tenderModel.put(key, tender);
    }

    public void remove(Integer key) { tenderModel.remove(key);}

    public Tender getModelElement(Integer key) {
        return tenderModel.get(key);
    }

    public TreeMap<Integer, Tender> getModel() {
        return this.tenderModel;
    }


    public List<String> listOfTenderNames() {
        TreeMap<Integer, Tender> treeMapBuff = this.tenderModel;
        ArrayList<String> buff = new ArrayList<>();
        for(Map.Entry<Integer,Tender> entry : treeMapBuff.entrySet()) {
            buff.add(entry.getValue().getTenderName());
        }
        return buff;
    }


}