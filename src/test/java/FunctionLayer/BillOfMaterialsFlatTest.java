/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emilv
 */
public class BillOfMaterialsFlatTest
{

    public BillOfMaterialsFlatTest()
    {
    }

    /**
     * Test of createPole method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreatePoleFullShed()
    {
        System.out.println("createPoleFullShed");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");

        order.setShedLength(210);
        order.setShedWidth(600);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createPole();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(11, BoM.get(0).getQty());
    }

    @Test
    public void testCreatePoleNoShed()
    {
        System.out.println("CreatePoleNoShed");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createPole();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(6, BoM.get(0).getQty());
    }

    @Test
    public void testCreatePoleHalfShed()
    {
        System.out.println("CreatePoleHalfShed");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");
        order.setShedLength(210);
        order.setShedWidth(270);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createPole();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(10, BoM.get(0).getQty());
    }

    @Test
    public void testCreatePoleShedLengthMoreThanMaxPoleDistance()
    {
        System.out.println("CreatePoleShedLengthMoreThanMaxPoleDistance");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");
        order.setShedLength(630);
        order.setShedWidth(330);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createPole();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(15, BoM.get(0).getQty());
    }

    @Test
    public void testCreatePoleFullShedLengthMoreThanMaxPoleDistance()
    {
        System.out.println("CreatePoleFullShedLengthMoreThanMaxPoleDistance");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");
        order.setShedLength(630);
        order.setShedWidth(570);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createPole();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(9, BoM.get(0).getQty());
    }

    @Test
    public void testCreatePoleHalfShedMorethanMaxPoleDistance()
    {
        System.out.println("CreatePoleHalfShedMorethanMaxPoleDistance");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");
        order.setShedLength(210);
        order.setShedWidth(330);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createPole();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(12, BoM.get(0).getQty());
    }

    /**
     * Test of createRafter method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateRafter()
    {
        System.out.println("createRafter");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");
        order.setShedLength(210);
        order.setShedWidth(330);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createRafter();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(9, BoM.get(0).getQty());
    }

    @Test
    public void testCreateShedFrame()
    {
        System.out.println("createShedFrame");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");
        order.setShedLength(330);
        order.setShedWidth(630);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createShedFrame();
        List<LineItem> BoM = instance.getBillOfMaterials();
        int qty = 0;
        for (LineItem lineItem : BoM)
        {
            qty += lineItem.getQty();
        }
        assertEquals(26, qty);
    }

    @Test
    public void testCreateWallPlateFullShed()
    {
        System.out.println("createWallPlateFullShed");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");
        order.setShedLength(330);
        order.setShedWidth(630);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createWallPlate();
        List<LineItem> BoM = instance.getBillOfMaterials();
        int qty = 0;
        for (LineItem lineItem : BoM)
        {
            qty += lineItem.getQty();
        }
        assertEquals(3, qty);
    }

    @Test
    public void testCreateWallPlateHalfShed()
    {
        System.out.println("createWallPlateHalfShed");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Med skur", "test", "test", "test", "test", "test");
        order.setShedLength(330);
        order.setShedWidth(330);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createWallPlate();
        List<LineItem> BoM = instance.getBillOfMaterials();
        int qty = 0;
        for (LineItem lineItem : BoM)
        {
            qty += lineItem.getQty();
        }
        assertEquals(3, qty);
    }

    @Test
    public void testCreateWallPlateNoShed()
    {
        System.out.println("createWallPlateNoShed");
        Order order = new Order(720, 630, "Fladt tag", 0, "ingen", "Uden skur", "test", "test", "test", "test", "test");
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createWallPlate();
        List<LineItem> BoM = instance.getBillOfMaterials();
        int qty = 0;
        for (LineItem lineItem : BoM)
        {
            qty += lineItem.getQty();
        }
        assertEquals(2, qty);
    }

}
