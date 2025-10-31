package co.edu.uniquindio.SOLID.Controlador;
import co.edu.uniquindio.SOLID.Model.DTO.*;
import co.edu.uniquindio.SOLID.Model.Producto;
import co.edu.uniquindio.SOLID.Service.Fachadas.InventarioFacade;
import co.edu.uniquindio.SOLID.Service.Fachadas.MinimercadoFacade;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InventarioController implements Initializable {

    @FXML private ComboBox<ProveedorDTO> cmbProveedores;
    @FXML private TitledPane tpCrearProveedor;
    @FXML private TextField txtProvNit;
    @FXML private TextField txtProvNombre;
    @FXML private TextField txtProvContacto;
    @FXML private TextField txtProvEmail;
    @FXML private TextField txtProvTelefono;
    @FXML private ComboBox<ProductoDTO> cmbProductoEntrada;
    @FXML private Spinner<Integer> spnCantidadEntrada;
    @FXML private Label lblResultadoEntrada;
    @FXML private TableView<ProductoDTO> tblProductosInv;
    @FXML private TableColumn<ProductoDTO, String> colInvSku;
    @FXML private TableColumn<ProductoDTO, String> colInvNombre;
    @FXML private TableColumn<ProductoDTO, Number> colInvPrecio;
    @FXML private TableColumn<ProductoDTO, Number> colInvStock;
    @FXML private Label lblMensaje;
    @FXML private Button btnInventario;

    private ObservableList<ProveedorDTO> proveedores;
    private ObservableList<ProductoDTO> productos;
    private InventarioFacade inventarioFacade;
    private ProveedorDTO proveedorSeleccionado;
    private MinimercadoFacade minimercadoFacade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventarioFacade= new InventarioFacade();
        proveedores = FXCollections.observableArrayList(inventarioFacade.getProveedores());
        minimercadoFacade = new MinimercadoFacade();
        productos = FXCollections.observableArrayList(minimercadoFacade.obtenerTodosLosProductos());
        cargarProveedores();
        configurarSeleccionListaProveedores();
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
        cargarProductosEnTabla();
    }

    private void cargarProveedores() {
        proveedores.clear();
        proveedores.addAll(inventarioFacade.getProveedores());
        if (cmbProveedores != null) {
            cmbProveedores.setItems(proveedores);
        }

        mostrarMensaje("Proveedores cargados: " + proveedores.size(), false);
    }

    private void configurarSeleccionListaProveedores() {
        cmbProveedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                proveedorSeleccionado = newSelection;
                cargarDatosEnFormulario(newSelection);
            }
        });
    }

    private void cargarDatosEnFormulario(ProveedorDTO proveedor) {
        txtProvNit.setText(proveedor.getNit());
        txtProvNit.setDisable(true);
        txtProvNombre.setText(proveedor.getNombre());
        txtProvNombre.setDisable(true);
        txtProvContacto.setText(proveedor.getContacto());
        txtProvEmail.setText(proveedor.getEmail());
        txtProvTelefono.setText(proveedor.getTelefono());
    }

    void limpiarFormulario() {
        txtProvNit.clear();
        txtProvNit.setDisable(false);
        txtProvNombre.clear();
        txtProvNombre.setDisable(false);
        txtProvContacto.clear();
        txtProvEmail.clear();
        txtProvTelefono.clear();

        proveedorSeleccionado = null;
        cmbProveedores.getSelectionModel().clearSelection();
        mostrarMensaje("Formulario limpio - Listo para nuevo cliente", false);
    }

    @FXML
    void mostrarCrearProveedor() {
        if (tpCrearProveedor != null) tpCrearProveedor.setExpanded(!tpCrearProveedor.isExpanded());
        limpiarFormulario();
    }

    @FXML
    void crearProveedor() {
        try {
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
            if (inventarioFacade.agregarProveedor(nuevoProveedor)) {
                cargarProveedores();
                limpiarFormulario();
                mostrarMensaje("Proveedor agregado exitosamente", false);
            } else {
                mostrarMensaje("Error: Ya existe un proveedor con el NIT" + nuevoProveedor.getNit(), true);
            }

        } catch (Exception e) {
            mostrarMensaje("Error al agregar cliente: " + e.getMessage(), true);
            e.printStackTrace();
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
            cargarProveedores();
            limpiarFormulario();
            mostrarMensaje("Proveedor actualizado exitosamente", false);

        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        if (lblMensaje != null) {
            lblMensaje.setText(mensaje);
        }
        lblMensaje.setStyle(esError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }

    @FXML
    void eliminarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        try {
            inventarioFacade.eliminarProveedor(nit);
            proveedores.removeIf(p -> p.getNit().equals(nit));
            if (cmbProveedores != null) cmbProveedores.setItems(FXCollections.observableArrayList(proveedores));
            limpiarFormulario();
            mostrarMensaje("Proveedor eliminado exitosamente", false);
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void activarProveedor() {
        String nit = txtProvNit != null ? txtProvNit.getText() : null;
        if (nit == null || nit.trim().isEmpty()) { mostrarError("El NIT es obligatorio"); return; }
        try {
            ProveedorDTO proveedorDTO=inventarioFacade.buscarProveedorDTO(nit);
            inventarioFacade.activarProveedor(proveedorDTO);
            mostrarMensaje("Proveedor activado exitosamente", false);
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
            mostrarMensaje("Proveedor Inactivado exitosamente", false);
            if (cmbProveedores != null) cmbProveedores.setItems(FXCollections.observableArrayList(proveedores));
        } catch (IllegalArgumentException e) { mostrarError(e.getMessage()); }
    }

    @FXML
    void confirmarEntradaInventario() {
        ProveedorDTO proveedorDTO = cmbProveedores != null ? cmbProveedores.getValue() : null;
        ProductoDTO prod = cmbProductoEntrada != null ? cmbProductoEntrada.getValue() : null;
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
        List<ItemEntradaDTO> item = new ArrayList<>();
        item.add(new ItemEntradaDTO(prod.getSku(), cant));
        EntradaInventarioDTO dto = new EntradaInventarioDTO(proveedorDTO.getNit(), item);
        
        try {
            inventarioFacade.registrarEntrada(dto);
            Producto producto = inventarioFacade.buscarProducto(prod.getSku());
            if (lblResultadoEntrada != null)
                lblResultadoEntrada.setText("Entrada confirmada. Stock " + prod.getSku() + ": " + producto.getStock());
            if (tblProductosInv != null) tblProductosInv.refresh();
            cargarProductosEnTabla();
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
        cargarProductosEnTabla();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public void cargarProductosEnTabla() {
        productos.clear();
        productos.addAll(minimercadoFacade.obtenerTodosLosProductos());
        tblProductosInv.setItems(productos);
        tblProductosInv.refresh();
    }

    @FXML
    void actualizarInventario(ActionEvent event) {
        cargarProductosEnTabla();
    }
}

