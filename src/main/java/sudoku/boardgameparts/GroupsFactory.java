package sudoku.boardgameparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GroupsFactory {

    public IGroup createGroup(String type, int size) {
        if (Objects.equals(type, "box")) {
            return new BoxGroup(size);
        } else if (Objects.equals(type, "line")) {
            return new LineGroup(size);
        }
        return null;
    }

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