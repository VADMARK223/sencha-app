/**
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.pages.personnel.View', {
    extend: 'Ext.panel.Panel',
    xtype: 'personnel-page-view',

    title: 'Personnel',

    // layout: 'fit',
    // region: 'center',
    // autoScroll: true,

    controller: 'personnel-page-controller',
    viewModel: 'personnel-page-view-model',

    items: [
        {
            xtype: 'personnel-grid-view',
            reference: 'personnelgrid-ref',
            flex: 1,
            listeners: {
                selectionchange: 'onPersonnelGridSelectionChange'
            }
        },
        {
            bodyPadding: 10,
            flex: 1,
            bind: {
                title: '{personnelName ? personnelName + "\'s details" : "Choose personnel"}',
                html: 'Address: {address ? address : "-"}'
            }
        },
    ]
});