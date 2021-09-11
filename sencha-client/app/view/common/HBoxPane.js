/**
 * @author Markitanov Vadim
 * @since 11.09.2021
 */
Ext.define('Sencha.classic.view.common.HBoxPane', {
    extend: 'Ext.container.Container',
    requires: [
        'Ext.layout.container.HBox'
    ],
    xtype: 'hbox-pane',
    layout: {
        type: 'hbox'
    }
});