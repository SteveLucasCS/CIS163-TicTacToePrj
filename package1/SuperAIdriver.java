package package1;
public class SuperAIdriver {
	public static void main(String[] args) {
		CellStatus[][] testBoard = {
			{
				CellStatus.EMPTY,
				CellStatus.EMPTY,
				CellStatus.EMPTY
			},
			{
				CellStatus.EMPTY,
				CellStatus.EMPTY,
				CellStatus.EMPTY
			},
			{
				CellStatus.EMPTY,
				CellStatus.EMPTY,
				CellStatus.EMPTY
			}
		};
		SuperAI test = new SuperAI(testBoard, "weights");

	}
}
