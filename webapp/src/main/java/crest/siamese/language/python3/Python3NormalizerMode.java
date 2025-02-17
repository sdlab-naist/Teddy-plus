package crest.siamese.language.python3;

import crest.siamese.language.NormalizerMode;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Python3NormalizerMode implements NormalizerMode {

    private static final char KEYWORD_OPTION = 'k';
    private static final char VALUE_OPTION = 'v';
    private static final char STRING_OPTION = 's';
    private static final char OPERATOR_OPTION = 'o';
    private static final char NAME_OPTION = 'w'; // same option character as Java clone search

	/*This part for Idiom tokenization*/
    private static final char NEWLINE_OPTION = 'n';
    private static final char INDENT_OPTION = 'i';
    private static final char DEDENT_OPTION = 'd';

    private Map<Character, Boolean> map = Stream.of(new Object[][] {
            { KEYWORD_OPTION , false },
            { VALUE_OPTION, false },
            { STRING_OPTION, false },
            { OPERATOR_OPTION, false },
            { NAME_OPTION, false },
			{ NEWLINE_OPTION, false},
            { INDENT_OPTION, false},
            { DEDENT_OPTION, false},
    }).collect(Collectors.toMap(data -> (char) data[0], data -> (boolean) data[1]));

    /**
     * Constructor that refer to parent Object class. Required for calling Class::newInstance.
     */
    public Python3NormalizerMode() {
        super();
    }

    /**
     * Configures the normalizer mode according to configuration char array.
     * @param normOptions Normalization options
     */
    @Override
    public void configure(char[] normOptions) {
        for (char normOption: normOptions) {
            if (map.containsKey(normOption)) {
                map.put(normOption, true);
            }
        }
    }

    /**
     * Resets existing normalization configuration.
     */
    @Override
    public void reset() {
        for (Map.Entry<Character, Boolean> entry: map.entrySet()) {
            map.put(entry.getKey(), false);
        }
    }

    public boolean isKeywordToBeNormalised() {
        return map.get(KEYWORD_OPTION);
    }

    public boolean isValueToBeNormalised() {
        return map.get(VALUE_OPTION);
    }

    public boolean isStringToBeNormalised() {
        return map.get(STRING_OPTION);
    }

    public boolean isOperatorToBeNormalised() {
        return map.get(OPERATOR_OPTION);
    }

    public boolean isNameToBeNormalised() {
        return map.get(NAME_OPTION);
    }
	
	//For python idiom normalization

    public boolean isNewlineTobeNormalised() { return map.get(NEWLINE_OPTION); }

    public boolean isIndentTobeNormalised() { return map.get(INDENT_OPTION); }

    public boolean isDedentTobeNormalised() { return map.get(DEDENT_OPTION); }
}
