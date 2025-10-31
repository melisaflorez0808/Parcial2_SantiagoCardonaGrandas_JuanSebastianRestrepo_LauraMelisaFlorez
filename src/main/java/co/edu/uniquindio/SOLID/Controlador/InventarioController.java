package co.edu.uniquindio.SOLID.Controlador;

import co.edu.uniquindio.SOLID.Model.DTO.ClienteDTO;
import co.edu.uniquindio.SOLID.Model.DTO.ProveedorDTO;
import co.edu.uniquindio.SOLID.Model.EntradaInventario;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Model.Proveedor;
import co.edu.uniquindio.SOLID.Service.Fachadas.InventarioFacade;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.text.StyledEditorKit;
import java.net.URL;
import java.util.ResourceBundle;

public class InventarioController implements Initializable {

    @FXML private ComboBox<ProveedorDTO> cmbProveedores;
    @FXML private TitledPane tpCrearProveedor;
    @FXML private TextField txtProvNit;
    @FXML private TextField txtProvNombre;
    @FXML private TextField txtProvContacto;
    @FXML private TextField txtProvEmail;
    @FXML private TextField txtProvTelefono;
    @FXML private ComboBox<Producto> cmbProductoEntrada;
    @FXML private Spinner<Integer> spnCantidadEntrada;
    @FXML private Label lblResultadoEntrada;
    @FXML private TableView<Producto> tblProductosInv;
    @FXML private TableColumn<Producto, String> colInvSku;
    @FXML private TableColumn<Producto, String> colInvNombre;
    @FXML private TableColumn<Producto, Number> colInvPrecio;
    @FXML private TableColumn<Producto, Number> colInvStock;

    private ObservableList<ProveedorDTO> proveedores;
    private ObservableList<Producto> productos;
    private Minimercado minimercado = Minimercado.getInstancia();////SE DEBE QUITAR AL FINAL
    private InventarioFacade inventarioFacade;
    private ProveedorDTO proveedorDTO;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventarioFacade=new InventarioFacade();
        proveedores = FXCollections.observableArrayList(inventarioFacade.getProveedores());
        productos = FXCollections.observableArrayList(minimercado.getProductos());
        
        if (cmbProveedores != null) {
            cmbProveedores.setItems(proveedores);
            cmbProveedores.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionado) -> {
                if (seleccionado != null) {
                    if (txtProvNit != null) txtProvNit.setText(seleccionado.getNit());
                    if (txtProvNombre != null) txtProvNombre.setText(seleccionado.getNombre());
                    if (txtProvContacto != null) txtProvContacto.setText(seleccionado.getContacto());
                    if (txtProvEmail != null) txtProvEmail.setText(seleccionado.getEmail());
                    if (txtProvTelefono != null) txtProvTelefono.setText(seleccionado.getTelefono());
                }
            });
        }
        if (cmbProductoEntrada != null) {
            cmbProductoEntrada.setItems(productos);
        }
        if (spnCantidadEntrada != null) {
            spnCantidadEntrada.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        }
        if (tblProductosInv != null) {
            colInvSku.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getSku()));
            colInvNombre.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getNombre()));
            colInvPrecio.setCellValueFactory(cd -> new SimpleDoubleProperty(cd.getValue().getPrecio()));
            colInvStock.setCellValueFactory(cd -> new SimpleIntegerProperty(cd.getValue().getStock()));
            tblProductosInv.setItems(productos);
        }
        if (tpCrearProveedor != null) tpCrearProveedor.setExpanded(false);
    }

    @FXML
    void mostrarCrearProveedor() {
        if (tpCrearProveedor != null) tpCrearProveedor.setExpanded(!tpCrearProveedor.isExpanded());
    }

    @FXML
    void crearProveedor() {
        if (!validarCamposProveedor()) {
            return;
        }

        ProveedorDTO nuevoProveedor = new ProveedorDTO(
                txtProvNit.getText().trim(),
                txtProvNombre.getText().trim(),
                txtProvContacto.getText().trim(),
                txtProvEmail.getText().trim(),
                txtProvTelefono.getText().trim()
        );

        try {
            inventarioFacade.agregarProveedor(nuevoProveedor);
            if (cmbProveedores != null) cmbProveedores.getSelectionModel().select(nuevoProveedor);
            if (lblResultadoEntrada != null)
                lblResultadoEntrada.setText("Proveedor creado: " + nuevoProveedor.getNombre());
            if (tpCrearProveedor != null) tpCrearProveedor.setExpanded(false);
            if (txtProvNit != null) txtProvNit.clear();
            if (txtProvNombre != null) txtProvNombre.clear();
            if (txtProvContacto != null) txtProvContacto.clear();
            if (txtProvEmail != null) txtProvEmail.clear();
            if (txtProvTelefono != null) txtProvTelefono.clear();
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }


    private boolean validarCamposProveedor() {
        if (txtProvNit.getText().trim().isEmpty()) {
            mostrarError("El nit  es obligatorio");
            return false;
        }

        if (txtProvNombre.getText().trim().isEmpty()) {
            mostrarError("El nombre es obligatorio");
            return false;
        }

        if (txtProvContacto.getText().trim().isEmpty()) {
            mostrarError("El contacto es obligatorio");
            return false;
        }

        if (txtProvTelefono.getText().trim().isEmpty()) {
            mostrarError("El teléfono es obligatorio");
            return false;
        }

        String correo = txtProvEmail.getText().trim();
        if (!correo.contains("@") || !correo.contains(".")) {
            mostrarError("El formato del correo no es válido");
            return false;
        }

        return true;
    }

    @FXML
    void actualizarProveedor() {

        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        String nombre = txtProvNombre != null ? txtProvNombre.getText() : null;
        String contacto = txtProvContacto != null ? txtProvContacto.getText() : null;
        String email = txtProvEmail != null ? txtProvEmail.getText() : null;
        String telefono = txtProvTelefono != null ? txtProvTelefono.getText() : null;
        ProveedorDTO proveedorDTO = new ProveedorDTO(nit, nombre, contacto, email, telefono);
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        try {
            inventarioFacade.actualizarProveedor(proveedorDTO);
            for (int i = 0; i < proveedores.size(); i++) {
                if (proveedores.get(i).getNit().equals(nit)) { proveedores.set(i, proveedorDTO); break; }
            }
            if (cmbProveedores != null) cmbProveedores.setItems(FXCollections.observableArrayList(proveedores));
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void eliminarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        try {
            inventarioFacade.eliminarProveedor(nit);
            proveedores.removeIf(p -> p.getNit().equals(nit));
            if (cmbProveedores != null) cmbProveedores.setItems(FXCollections.observableArrayList(proveedores));
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void activarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        try {
            ProveedorDTO proveedorDTO=inventarioFacade.buscarProveedorDTO(nit);
            inventarioFacade.activarProveedor(proveedorDTO);
            if (cmbProveedores != null) cmbProveedores.setItems(FXCollections.observableArrayList(proveedores));
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void inactivarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        try {
            ProveedorDTO proveedorDTO=inventarioFacade.buscarProveedorDTO(nit);
            inventarioFacade.inactivarProveedor(proveedorDTO);
            if (cmbProveedores != null) cmbProveedores.setItems(FXCollections.observableArrayList(proveedores));
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void confirmarEntradaInventario() {
        ProveedorDTO proveedorDTO = cmbProveedores != null ? cmbProveedores.getValue() : null;
        Producto prod = cmbProductoEntrada != null ? cmbProductoEntrada.getValue() : null;
        Integer cant = spnCantidadEntrada != null ? spnCantidadEntrada.getValue() : 0;
        
        // Validaciones de campos
        if (proveedorDTO == null) {
            mostrarError("Seleccione un proveedor");
            return;
        }
        if (prod == null) {
            mostrarError("Seleccione un producto");
            return;
        }
        if (cant == null || cant <= 0) {
            mostrarError("Cantidad inválida");
            return;
        }
        
        try {
            minimercado.registrarEntradaInventario(proveedorDTO, prod, cant);
            if (lblResultadoEntrada != null) lblResultadoEntrada.setText("Entrada confirmada. Stock " + prod.getSku() + ": " + prod.getStock());
            if (tblProductosInv != null) tblProductosInv.refresh();
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}


