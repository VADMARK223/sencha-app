/**
 * Модель документов пользователя
 * @author Markitanov Vadim
 * @since 04.09.2021
 */
Ext.define('Sencha.classic.model.Doc', {
    extend: 'Ext.data.Model',

    fields: [
        {
            name: 'name',
            type: 'string'
        }, {
            name: 'size',
            type: 'number',
        }, {
            name: 'sizeValue',
            type: 'string',
            persist: false, // Не отсылать виртуальное поля на сервер
            depends: ['size'],
            calculate: function (data) {
                return Ext.String.format('{0} MB', data.size);
            }
        }
    ]
});