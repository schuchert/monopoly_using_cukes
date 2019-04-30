package org.shoe.monopoly.steps;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<>("string_list", "\\s*.*\\s*", List.class, this::parseNames));
    }

    private List<String> parseNames(String listOfStrings) {
        return Arrays.asList(listOfStrings.split("\\s*,\\s*"));
    }
}
