/**
 * @author Markitanov Vadim
 * @since 04.09.2021
 */
Ext.define('Sencha.classic.view.pages.user.docsGrid.ViewModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.user-page-docs-grid-view-model',

    requires: [
        'Sencha.classic.model.Doc'
    ],

    stores: {
        userDocsStore: {
            model: 'Sencha.classic.model.Doc' // Модель данных
        }
    }
});