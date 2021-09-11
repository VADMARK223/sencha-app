/**
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.pages.personnel.grid.ViewModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.personnelGrid-view-model',

    requires: [
        'Sencha.classic.store.PersonnelStore'
    ],

    stores: {
        personnelStore: {
            type: 'personnel-store',
        }
    }
});
