package sudoku.boardgameparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GroupsFactory {

    public List<IGroup> createGroups(String type, int size, int amount) {
        List<IGroup> groups = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (Objects.equals(type, "box")) {
                groups.add(new BoxGroup(size));
            } else if (Objects.equals(type, "line")) {
                groups.add(new LineGroup(size));
            }
        }
        return groups;
    }
}