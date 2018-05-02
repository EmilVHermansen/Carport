/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.List;

/**
 * ALL UNITS ARE IN MILIMETER
 *
 * @author s_ele
 */
public class BillOfMaterials {

    private List<LineItem> billOfMaterials;
    private final int maxPoleDistance = 3000; // 3000 mm
    private Order order;
    // Attributes that are used from order:
    private int idOrder;
    private boolean fullShed;
    private int length;
    private int width;
    private boolean shed = false;
    private int shedLength = 0;
    private int shedWidth = 0;

    public BillOfMaterials(Order order) {
        this.order = order;
        this.idOrder = order.getIdOrder();
        this.length = order.getLength();
        this.width = order.getWidth();
        if (order.getShed().equals("shed")) {
            shed = true;
            this.shedLength = order.getShedLength();
            this.shedWidth = order.getShedWidth();
            fullShed = order.getShedWidth() == (order.getWidth() - 700); // shedWidth == width
        }

    }

    private int length() {
        return order.getLength();
    }

    public void createBoM() {

        createPole();
        createWallPlate();
        createRafter();
        createFasciaAndWaterBoard();
        createRoofPlate();

    }

    public void createPole() {

        LineItem pole = new LineItem("stk", "Stolper nedgraves 90 cm. i jord", idOrder, 1);
        int poleAmount = 2; // minimum amount of poles

        if (shed) {
            poleAmount += 4; // mimimum addition of shedpoles (1 for the door)
            if (shedWidth > maxPoleDistance) {
                poleAmount += 2;
            }
            if (fullShed) {
                poleAmount -= 1;
            }
        }

        if (fullShed) {
            length -= shedLength;
        }

        poleAmount += (length / maxPoleDistance * 2);

        if (length % maxPoleDistance > 1200) {
            poleAmount += 2;
        }
        pole.setQty(poleAmount);
        pole.setLength(3000);
        billOfMaterials.add(pole);

    }

    public void createWallPlate() {

        LineItem wallPlateCarport = new LineItem("stk", "Remme i sider, sadles ned i stolper", idOrder, 2);

        if (shed) {
            LineItem wallPlateShed = new LineItem("stk", "Remme i sider, sadles ned i stolper (skur del, deles)", idOrder, 2);
            wallPlateShed.setLength(shedLength * 2);
            wallPlateShed.setQty(1);
            billOfMaterials.add(wallPlateShed);

            if (fullShed) {
                wallPlateCarport.setLength(length - shedLength);
                wallPlateCarport.setQty(2);
            } else {
                wallPlateCarport.setLength(length);
                wallPlateCarport.setQty(1);

            }
        } else {
            wallPlateCarport.setLength(length);
            wallPlateCarport.setQty(2);
        }
        billOfMaterials.add(wallPlateCarport);
    }

    public void createRafter() {

        LineItem rafter = new LineItem("stk", "Spær, monteres på rem", idOrder, 3);
        rafter.setLength(length);
        int qty = 2;
        qty += length / 600;
        if (length % 600 > 0) {
            qty++;
        }
        rafter.setQty(qty);
        billOfMaterials.add(rafter);
    }

    private void createFasciaAndWaterBoard() {
        LineItem fasciaBoardUnderFrontBack = new LineItem("stk", "understernbrædder til for & bag ende", idOrder, 4);
        fasciaBoardUnderFrontBack.setQty(4);

        LineItem fasciaBoardUnderSides = new LineItem("stk", "understernbrædder til siderne", idOrder, 4);
        fasciaBoardUnderSides.setQty(4);

        LineItem fasciaBoardFront = new LineItem("stk", "oversternbrædder til forenden", idOrder, 5);
        fasciaBoardFront.setQty(2);

        LineItem fasciaBoardSides = new LineItem("stk", "oversternbrædder til siderne", idOrder, 5);
        fasciaBoardSides.setQty(4);

        LineItem waterBoardFront = new LineItem("stk", "vandbrædt på stern i forende", idOrder, 6);
        waterBoardFront.setQty(2);

        LineItem waterBoardSides = new LineItem("stk", "vandbrædt på stern i sider", idOrder, 6);
        waterBoardSides.setQty(4);

        int frontBackLength = width - (width % 300);
        if (width % 300 > 0) {
            frontBackLength += 300;
        }
        fasciaBoardUnderFrontBack.setLength(frontBackLength);
        fasciaBoardFront.setLength(frontBackLength);
        waterBoardFront.setLength(frontBackLength);

        int sideLength = length - (length % 300);
        if (length % 300 != 0) {
            sideLength += 300;
        }

        fasciaBoardUnderSides.setLength(sideLength);
        fasciaBoardSides.setLength(sideLength);
        waterBoardSides.setLength(sideLength);

        billOfMaterials.add(fasciaBoardUnderFrontBack);
        billOfMaterials.add(fasciaBoardUnderSides);
        billOfMaterials.add(fasciaBoardFront);
        billOfMaterials.add(fasciaBoardSides);
        billOfMaterials.add(waterBoardFront);
        billOfMaterials.add(waterBoardSides);

    }

    // TODO!!!!!!!!!!!!!!!!
    private void createRoofPlate() {
        LineItem roofPlate1 = new LineItem("stk", "tagplader monteres på spær", idOrder, 7);
        int qty = width() / 1040;

        if (width() % 1040 > 0) {
            qty++;
        }

        roofPlate1.setQty(qty);

        if (length() > 6000) {
            roofPlate1.setLength(6000);
            LineItem roofPlate2 = new LineItem("stk", "tagplader monteres på spær", idOrder, 7);
            roofPlate2.setQty(qty);
            int length2 = length() - 6000;
            if (length2 <= 2400) {
                roofPlate2.setLength(2400);
            }
//            else if (length2 <= 3000) {
//                roofPlate2.setLength(3000);
//            } else if (length2 <= 3600) {
//                roofPlate2.setLength(3600);
//            } else if (length2 <= 4200) {
//                roofPlate2.setLength(4200);
//            } else if (length2 <= 4800) {
//                roofPlate2.setLength(4800);
//            } else if (length2 <= 6000) {
//                roofPlate2.setLength(6000);
            billOfMaterials.add(roofPlate1);
            billOfMaterials.add(roofPlate2);
        } else {
            int length = length();
            if (length <= 2400) {
                roofPlate1.setLength(2400);
            } else if (length <= 3000) {
                roofPlate1.setLength(3000);
            } else if (length <= 3600) {
                roofPlate1.setLength(3600);
            } else if (length <= 4200) {
                roofPlate1.setLength(4200);
            } else if (length <= 4800) {
                roofPlate1.setLength(4800);
            } else if (length <= 6000) {
                roofPlate1.setLength(6000);
            }
            billOfMaterials.add(roofPlate1);
        }

    }//CLASS
