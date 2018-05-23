/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
     * Test of getBillOfMaterials method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testGetBillOfMaterials()
    {
        System.out.println("getBillOfMaterials");
        BillOfMaterialsFlat instance = null;
        List<LineItem> expResult = null;
        List<LineItem> result = instance.getBillOfMaterials();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createBoM method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateBoM()
    {
        System.out.println("createBoM");
        Order order = new Order(52, 720, 630, "Fladt tag", 0, "ingen", "shed", "test", "test", "test", "test", "test", 28375);
        order.setShedLength(210);
        order.setShedWidth(600);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createBoM();
        assertNotNull(instance.getBillOfMaterials());
    }

    /**
     * Test of createPole method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreatePoleFullShed()
    {
        System.out.println("createPoleFullShed");
        Order order = new Order(52, 720, 630, "Fladt tag", 0, "ingen", "shed", "test", "test", "test", "test", "test", 28375);
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
        Order order = new Order(52, 720, 630, "Fladt tag", 0, "ingen", "shed", "test", "test", "test", "test", "test", 28375);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createPole();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(6, BoM.get(0).getQty());
    }
    
    @Test
    public void testCreatePoleHalfShed()
    {
        System.out.println("CreatePoleHalfShed");
        Order order = new Order(52, 720, 630, "Fladt tag", 0, "ingen", "shed", "test", "test", "test", "test", "test", 28375);
        order.setShedLength(210);
        order.setShedWidth(270);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createPole();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(10, BoM.get(0).getQty());
    }
    
    @Test
    public void testCreatePoleHalfShedMorethanMaxPoleDistance()
    {
        System.out.println("CreatePoleHalfShedMorethanMaxPoleDistance");
        Order order = new Order(52, 720, 630, "Fladt tag", 0, "ingen", "shed", "test", "test", "test", "test", "test", 28375);
        order.setShedLength(210);
        order.setShedWidth(330);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createPole();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(12, BoM.get(0).getQty());
    }

    /**
     * Test of createWallPlate method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateWallPlate()
    {
        System.out.println("createWallPlate");
        BillOfMaterialsFlat instance = null;
        instance.createWallPlate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRafter method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateRafter()
    {
        System.out.println("createRafter");
        Order order = new Order(52, 720, 630, "Fladt tag", 0, "ingen", "shed", "test", "test", "test", "test", "test", 28375);
        order.setShedLength(210);
        order.setShedWidth(330);
        BillOfMaterialsFlat instance = new BillOfMaterialsFlat(order);
        instance.createRafter();
        List<LineItem> BoM = instance.getBillOfMaterials();
        assertEquals(9, BoM.get(0).getQty());
    }

    /**
     * Test of createFasciaAndWaterBoard method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateFasciaAndWaterBoard()
    {
        System.out.println("createFasciaAndWaterBoard");
        BillOfMaterialsFlat instance = null;
        instance.createFasciaAndWaterBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRoofPlate method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateRoofPlate()
    {
        System.out.println("createRoofPlate");
        BillOfMaterialsFlat instance = null;
        instance.createRoofPlate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createZBacksideDoor method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateZBacksideDoor()
    {
        System.out.println("createZBacksideDoor");
        BillOfMaterialsFlat instance = null;
        instance.createZBacksideDoor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createShedFrame method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateShedFrame()
    {
        System.out.println("createShedFrame");
        BillOfMaterialsFlat instance = null;
        instance.createShedFrame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createShedCladding method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateShedCladding()
    {
        System.out.println("createShedCladding");
        BillOfMaterialsFlat instance = null;
        instance.createShedCladding();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPerforatedBand method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreatePerforatedBand()
    {
        System.out.println("createPerforatedBand");
        BillOfMaterialsFlat instance = null;
        instance.createPerforatedBand();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createShedDoorKnob method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateShedDoorKnob()
    {
        System.out.println("createShedDoorKnob");
        BillOfMaterialsFlat instance = null;
        instance.createShedDoorKnob();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createShedDoorHinge method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateShedDoorHinge()
    {
        System.out.println("createShedDoorHinge");
        BillOfMaterialsFlat instance = null;
        instance.createShedDoorHinge();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUniversalBracket method, of class BillOfMaterialsFlat.
     */
    @Test
    public void testCreateUniversalBracket()
    {
        System.out.println("createUniversalBracket");
        BillOfMaterialsFlat instance = null;
        instance.createUniversalBracket();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
