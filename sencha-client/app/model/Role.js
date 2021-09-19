/**
 * @author Markitanov Vadim
 * @since 19.09.2021
 */
Ext.define('Sencha.classic.model.Role', {
    extend: 'Ext.data.Model',

    fields: [
        {
            name: 'id',
            type: 'number'
        },
        'name'
    ]
});