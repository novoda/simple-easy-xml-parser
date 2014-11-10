package com.novoda.sax;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;


public class ChildrenShould
{
    public static final String FIRST_STRING_WITH_EQUAL_HASH = "0-42L";

    public static final String SECOND_STRING_WITH_EQUAL_HASH = "0-43-";

    private Children children = new Children();

    private Element parent = new Element( null, "", "test", 0 );


    @Test
    public void create_new_child_with_equal_hash_uri_and_local()
        throws Exception
    {
        Element element = children.getOrCreate( parent, FIRST_STRING_WITH_EQUAL_HASH, FIRST_STRING_WITH_EQUAL_HASH );

        Element anotherElement =
            children.getOrCreate( parent, SECOND_STRING_WITH_EQUAL_HASH, SECOND_STRING_WITH_EQUAL_HASH );

        assertThat( element ).isNotSameAs( anotherElement );
    }

    @Test
    public void same_child_with_equal_uri_and_local()
    {
        Element element = children.getOrCreate( parent, FIRST_STRING_WITH_EQUAL_HASH, FIRST_STRING_WITH_EQUAL_HASH );
        Element anotherElement =
            children.getOrCreate( parent, FIRST_STRING_WITH_EQUAL_HASH, FIRST_STRING_WITH_EQUAL_HASH );

        assertThat( element ).isSameAs( anotherElement );
    }

    @Test
    public void return_proper_child_with_equal_hash_uri_and_local()
    {
        children.getOrCreate( parent, FIRST_STRING_WITH_EQUAL_HASH, FIRST_STRING_WITH_EQUAL_HASH );

        Element anotherElement =
            children.getOrCreate( parent, SECOND_STRING_WITH_EQUAL_HASH, SECOND_STRING_WITH_EQUAL_HASH );

        assertThat( children.get( SECOND_STRING_WITH_EQUAL_HASH, SECOND_STRING_WITH_EQUAL_HASH ) ).isSameAs(
            anotherElement );
    }

}
