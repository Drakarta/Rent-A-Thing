package ant.rentathing.util;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import lombok.Getter;

import java.util.List;

@Getter
public class GridField {
    private GridPane grid;
    private int width;
    private int currentRow;
    private int currentCol;

    public GridField(int width) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        for (int i = 0; i < width; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / width);
            gridPane.getColumnConstraints().add(col);
        }

        this.grid = gridPane;
        this.width = width;
        this.currentRow = 0;
        this.currentCol = 0;
    }

    public void nextGrid() {
        currentCol++;
        if (currentCol >= width) {
            currentCol = 0;
            currentRow++;
        }
    }

    public void addToGrid(Node node) {
        grid.add(node, currentCol, currentRow);
        nextGrid();
    }

    public void addToGridFull(Node node) {
        grid.add(node, currentCol, currentRow, width, 1);
        for (int i = 0 ; i < width ; i++) {
            nextGrid();
        }
    }

    public void addToGrid(List<Node> nodes) {
        for (Node node : nodes) {
            addToGrid(node);
        }
    }
}
