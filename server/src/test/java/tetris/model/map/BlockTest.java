package tetris.model.map;

import com.xoxoms.tetris.server.model.map.BlockType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BlockTest {

    @Test
    void testRandomBlock() {

        Set<BlockType> blockTypes = Stream.of(BlockType.values())
                .collect(Collectors.toSet());

        do {

            blockTypes.remove(BlockType.pickRandom());

        } while (blockTypes.size() != 0);

        Assertions.assertEquals(blockTypes.size(), 0);
    }
}
